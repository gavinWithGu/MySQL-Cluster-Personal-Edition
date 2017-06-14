package com.gavin.study.mysqlcluster.core.zookeeper.lb;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.gavin.study.mysqlcluster.utils.ConfigFileUtil;

public class ZooKeeperServiceDiscovery {
	private String rootNode = ConfigFileUtil.ZK_SERVICE_ROOTNODE;
	private ZooKeeper zk;
	private Stat stat = new Stat();

	private static final byte[] lock = new byte[0];

//	private List<String> serviceRepos = new ArrayList<String>();

	private static final ZooKeeperServiceDiscovery instance = new ZooKeeperServiceDiscovery();

	private ZooKeeperServiceDiscovery() {
	}

	public static ZooKeeperServiceDiscovery getInstance() {
		return instance;
	}

	public String getServiceEndpoint(int type) {
		LBStrategy lbStrategy = LBStrageyFactory.getInstance().getLBStrategy(
				type, ServerRepos.getInstance().getServiceRepos());
		return lbStrategy.pickup().toString();
	}

	public void updateServiceRepos() throws Exception {
		synchronized (lock) {
			List<String> newServerList = new ArrayList<String>();

			// 获取并监听子节点变化
			// watch参数为true, 表示监听子节点变化事件.
			// 每次都需要重新注册监听, 因为一次注册, 只能监听一次事件, 如果还想继续保持监听, 必须重新注册
			List<String> subList = zk.getChildren(rootNode, true);
			for (String subNode : subList) {
				// 获取每个子节点下关联的server地址
				byte[] data = zk
						.getData(rootNode + "/" + subNode, false, stat);
				newServerList.add(new String(data, "utf-8"));
			}

			// 替换server列表
			ServerRepos.getInstance().updateRepos(newServerList);

			System.out.println("server list updated: " + newServerList);
		}
	}

	public List<String> getServiceRepos() {
		return ServerRepos.getInstance().getServiceRepos();
	}

	public void init() {
		new Thread(new ZookeeperServiceListener()).start();
	}

	class ZookeeperServiceListener implements Runnable {

		public void run() {
			try {
				this.connectZookeeper();

				updateServiceRepos();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 连接zookeeper
		 */
		public void connectZookeeper() throws Exception {
			zk = new ZooKeeper(ConfigFileUtil.ZK_HOST,
					ConfigFileUtil.ZK_CLIENT_TIMEOUT, new Watcher() {
						public void process(WatchedEvent event) {
							// 如果节点下的子节点变化事件, 更新server列表, 并重新注册监听
							if (event.getType() == EventType.NodeChildrenChanged
									&& (rootNode).equals(event.getPath())) {
								try {
									updateServiceRepos();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					});
		}
	}
}
