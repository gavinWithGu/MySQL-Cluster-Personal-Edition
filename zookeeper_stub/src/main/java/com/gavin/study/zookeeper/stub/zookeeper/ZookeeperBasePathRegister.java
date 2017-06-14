package com.gavin.study.zookeeper.stub.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import com.gavin.study.zookeeper.stub.utils.ConfigFileUtil;

/**
 * 创建指定的目录
 * @author gavin
 *
 */
public class ZookeeperBasePathRegister {
	private static String ROOT_NODE = "/gavin";
	private static String SERVICE_BASE_NODE = "/services";
	private static String SERVICE_SAMPLE_NODE = "/sample";
	
	public static void main(String[] args) throws IOException, KeeperException,
			InterruptedException {
		// 创建一个Zookeeper实例，第一个参数为目标服务器地址和端口，第二个参数为Session超时时间，第三个为节点变化时的回调方法
		ZooKeeper zk = new ZooKeeper(ConfigFileUtil.ZK_HOST,
				ConfigFileUtil.ZK_CLIENT_TIMEOUT, new Watcher() {
					// 监控所有被触发的事件
					public void process(WatchedEvent event) {
					
					}
				});
		zk.create(ROOT_NODE+SERVICE_BASE_NODE,
				"{\"services\":\"services\"}".getBytes(),
				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		
		zk.create(ROOT_NODE+SERVICE_BASE_NODE+SERVICE_SAMPLE_NODE,
				"{\"sample\":\"sample\"}".getBytes(),
				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// 关闭session
		zk.close();
	}
}
