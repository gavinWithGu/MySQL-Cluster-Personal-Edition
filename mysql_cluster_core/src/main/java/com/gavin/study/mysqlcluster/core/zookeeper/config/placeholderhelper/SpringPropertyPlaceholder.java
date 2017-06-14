package com.gavin.study.mysqlcluster.core.zookeeper.config.placeholderhelper;

import java.util.Properties;

import org.apache.zookeeper.data.Stat;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.gavin.study.mysqlcluster.core.zookeeper.wrapper.ZkClientWrapper;
import com.gavin.study.mysqlcluster.utils.ConfigFileUtil;

public class SpringPropertyPlaceholder extends PropertyPlaceholderConfigurer {
	private static final byte[] lock = new byte[0];
	private String rootNode = ConfigFileUtil.ZK_CONFIGS_ROOTNODE;
	private Stat stat = new Stat();

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		try {
			updateConfigs(props);

			System.out.println(props);
		} catch (Exception e) {
			// TODO: Ignore
			e.printStackTrace();
		}
		
		super.processProperties(beanFactoryToProcess, props);
	}

	public void updateConfigs(Properties props) throws Exception {
		synchronized (lock) {
			for (final String path : ConfigFileUtil.ZK_CONFIGS_FILE_SPRING_COLLECTION) {
				String data = ZkClientWrapper.getInstance().getZkClient()
						.readData(rootNode + "/" + path, stat);
				props.putAll(new PropertyPlaceholderHelper().parseFromString(data));
			}
		}
		System.out.println("server configs updated: " + props.size());
	}
}
