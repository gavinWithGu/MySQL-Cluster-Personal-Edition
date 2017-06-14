package com.gavin.study.mysqlcluster.core.zookeeper.config.placeholderhelper;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.gavin.study.mysqlcluster.common.spring.context.BeanFactory;
import com.gavin.study.mysqlcluster.core.dbcluster.datasource.DynamicDataSource;
import com.gavin.study.mysqlcluster.core.zookeeper.pojo.DBInfo;
import com.gavin.study.mysqlcluster.utils.ConfigFileUtil;

public class DBClusterPropertyPlaceholder extends PropertyPlaceholderHelper {

	private String rootNode = ConfigFileUtil.ZK_CONFIGS_ROOTNODE;
	private ZooKeeper zk;
	private Stat stat = new Stat();

	private static final byte[] lock = new byte[0];

	private static Map<String, List<DBInfo>> configs = new HashMap<String, List<DBInfo>>();
	private static int dbNum = 0;

	private static final DBClusterPropertyPlaceholder instance = new DBClusterPropertyPlaceholder();

	private DBClusterPropertyPlaceholder() {
	}

	private void genMap(String subNode, ZkClient zkClient)
			throws KeeperException, InterruptedException,
			UnsupportedEncodingException {

		// 获取每个子节点下关联的server地址

		String data = zkClient.readData(rootNode + "/" + subNode, stat);

		Properties prop = this.parseFromString(data);

		int dbNumConfig = Integer
				.parseInt(prop.get("db.num").toString().trim());
		List<DBInfo> list = new ArrayList<DBInfo>();
		for (int i = 0; i < dbNumConfig; i++) {
			DBInfo dbinfo = new DBInfo(prop.getProperty("jdbc.url." + i),
					prop.getProperty("jdbc.username." + i),
					prop.getProperty("jdbc.password." + i),
					prop.getProperty("jdbc.driverClassName." + i),
					prop.getProperty("jdbc.sn." + i));

			if (prop.getProperty("jdbc.url." + i).contains(
					prop.getProperty("jdbc.url.master"))) {
				configs.put("shardNum:1", Arrays.asList(dbinfo));
			} else {
				list.add(dbinfo);
			}
		}
		// TODO：分库分表以后，shardNum:0中的shardNum可以扩展成具体的shard数目
		configs.put("shardNum:0", list);

		dbNum = dbNumConfig;
		
//		DynamicDataSource bean = (DynamicDataSource)BeanFactory.getInstance().getBeanByName("dynamicDataSource");
//		for (int i = 0; i < dbNum; i++) {
//			bean.getDataSourceMap(i+"");
//		}
	}

	public static DBClusterPropertyPlaceholder getInstance() {
		return instance;
	}

	public List<DBInfo> getContent(String key) {
		return configs.get(key);
	}

	public int getNum() {
		return dbNum;
	}

	public void updateConfigs(String path, ZkClient zkClient) throws Exception {
		synchronized (lock) {
			// 获取并监听子节点变化
			// watch参数为true, 表示监听子节点变化事件.
			// 每次都需要重新注册监听, 因为一次注册, 只能监听一次事件, 如果还想继续保持监听, 必须重新注册
			genMap(path,zkClient);
			
//			DynamicDataSource bean = (DynamicDataSource)BeanFactory.getInstance().getBeanByName("dynamicDataSource");
//			
//			for (int i = 0; i < dbNum; i++) {
//				bean.getDataSourceMap(i+"");
//			}
		}
		System.out.println("server configs updated: " + configs.size());
	}

	public Map<String, List<DBInfo>> getAllConfigs() {
		return configs;
	}
}
