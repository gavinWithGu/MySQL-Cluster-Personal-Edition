package com.gavin.study.mysqlcluster.core.zookeeper.config.listerner.impl;

import org.I0Itec.zkclient.ZkClient;

import com.gavin.study.mysqlcluster.core.zookeeper.config.listerner.IDynamicConfigFileListener;
import com.gavin.study.mysqlcluster.core.zookeeper.config.placeholderhelper.DBClusterPropertyPlaceholder;
import com.gavin.study.mysqlcluster.core.zookeeper.config.subscriber.ConfigChangeSubscriber;
import com.gavin.study.mysqlcluster.core.zookeeper.config.subscriber.impl.ZkConfigChangeSubscriberImpl;
import com.gavin.study.mysqlcluster.core.zookeeper.wrapper.ZkClientWrapper;
import com.gavin.study.mysqlcluster.utils.ConfigFileUtil;

//@Service("dynamicConfigFileListener")
public class DynamicConfigFileListenerImpl implements
		IDynamicConfigFileListener {

	private ConfigChangeSubscriber zkConfig = new ZkConfigChangeSubscriberImpl();
	
	private static final ZkClient zkClient = ZkClientWrapper.getInstance().getZkClient();
	@Override
	public void registerListener() {
		for (final String path : ConfigFileUtil.ZK_CONFIGS_FILE_COLLECTION) {
			
			try {
				DBClusterPropertyPlaceholder.getInstance().updateConfigs(path, zkClient);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			this.zkConfig.subscribe(path, new ConfigChangeListener() {
				public void configChanged(String key, String value) {
					System.out.println(path+"文件接收到数据变更通知: key=" + key
							+ ", value=" + value);
					try {
						DBClusterPropertyPlaceholder.getInstance().updateConfigs(key, zkClient);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
