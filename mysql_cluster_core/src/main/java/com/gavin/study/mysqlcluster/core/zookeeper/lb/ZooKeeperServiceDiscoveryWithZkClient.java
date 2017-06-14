package com.gavin.study.mysqlcluster.core.zookeeper.lb;

import java.util.LinkedList;
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import com.gavin.study.mysqlcluster.core.zookeeper.wrapper.ZkClientWrapper;
import com.gavin.study.mysqlcluster.utils.ConfigFileUtil;

public class ZooKeeperServiceDiscoveryWithZkClient {
	private String rootNode = ConfigFileUtil.ZK_SERVICE_ROOTNODE;
	private ZkClient zkClient = ZkClientWrapper.getInstance().getZkClient();;

	private static final byte[] lock = new byte[0];

//	private List<String> serviceRepos = new ArrayList<String>();

	private static final ZooKeeperServiceDiscoveryWithZkClient instance = new ZooKeeperServiceDiscoveryWithZkClient();

	private ZooKeeperServiceDiscoveryWithZkClient() {
	}

	public static ZooKeeperServiceDiscoveryWithZkClient getInstance() {
		return instance;
	}

	public String getServiceEndpoint(int type) {
		LBStrategy lbStrategy = LBStrageyFactory.getInstance().getLBStrategy(
				type, ServerRepos.getInstance().getServiceRepos());
		return lbStrategy.pickup().toString();
	}

	public void updateServiceRepos() throws Exception {
		synchronized (lock) {
			LinkedList<String> newServerList = new LinkedList<String>();

			List<String> children = zkClient.getChildren(rootNode);

			for (String subNode : children) {
				// 获取每个子节点下关联的server地址
				newServerList.add(subNode);
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
			zkClient.subscribeChildChanges(rootNode, new IZkChildListener() {
				@Override
				public void handleChildChange(String parentPath,
						List<String> currentChilds) throws Exception {
					for (String string : currentChilds) {
						System.out.println(string);
					}
					updateServiceRepos();
				}
			});
		}
	}
}
