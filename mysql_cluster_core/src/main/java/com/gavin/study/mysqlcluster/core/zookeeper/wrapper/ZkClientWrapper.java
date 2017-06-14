package com.gavin.study.mysqlcluster.core.zookeeper.wrapper;

import org.I0Itec.zkclient.ZkClient;

import com.gavin.study.mysqlcluster.utils.ConfigFileUtil;

public class ZkClientWrapper {
	private static ZkClient zkClient;
	
	private static final ZkClientWrapper instance = new ZkClientWrapper();

	static {
		zkClient = new ZkClient(ConfigFileUtil.ZK_HOST,
				ConfigFileUtil.ZK_CLIENT_TIMEOUT,
				ConfigFileUtil.ZK_CLIENT_TIMEOUT,
				new com.gavin.study.mysqlcluster.utils.ZkUtils.StringSerializer(
						"UTF-8"));
	}

	private ZkClientWrapper() {
	}

	public static ZkClientWrapper getInstance() {
		return instance;
	}

	public ZkClient getZkClient() {
		return zkClient;
	}

}
