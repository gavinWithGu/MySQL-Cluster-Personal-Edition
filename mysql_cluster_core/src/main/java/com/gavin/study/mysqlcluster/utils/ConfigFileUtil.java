package com.gavin.study.mysqlcluster.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConfigFileUtil {

	public static Properties propsSystem = new Properties();

	public static String ZK_HOST;
	public static int ZK_CLIENT_TIMEOUT;

	public static String ZK_CONFIGS_ROOTNODE;
	public static String ZK_SERVICE_ROOTNODE;
	
	public static  List<String> ZK_CONFIGS_FILE_COLLECTION = new ArrayList<String>();
	public static  List<String> ZK_CONFIGS_FILE_SPRING_COLLECTION = new ArrayList<String>();
	
	static {
		InputStream inSystem = ConfigFileUtil.class.getClassLoader()
				.getResourceAsStream("system.properties");

		try {
			propsSystem.load(inSystem);
			ZK_HOST = propsSystem.getProperty("zk.host");
			ZK_CLIENT_TIMEOUT = Integer.parseInt(propsSystem
					.getProperty("zk.client.timeout"));
			
			ZK_CONFIGS_ROOTNODE = propsSystem.getProperty("zk.configs.rootnode");
			ZK_SERVICE_ROOTNODE = propsSystem.getProperty("zk.service.rootnode");
			
			String configs =propsSystem.getProperty("zk.configs.file.collection");
			String springConfigs =propsSystem.getProperty("zk.configs.file.spring.collection");
			
			ZK_CONFIGS_FILE_COLLECTION = Arrays.asList(configs.split(","));
			
			ZK_CONFIGS_FILE_SPRING_COLLECTION= Arrays.asList(springConfigs.split(","));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}
}
