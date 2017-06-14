package com.gavin.study.zookeeper.stub.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigFileUtil {

	public static Properties props = new Properties();
	public static Properties propsSystem = new Properties();

	public static String JDBC_DRIVERCLASSNAME;
	public static String JDBC_URL;
	public static String JDBC_USERNAME;
	public static String JDBC_PASSWORD;

	public static String HBASE_ZOOKEEPER_QUORUM;
	public static String HBASE_ZOOKEEPER_QUORUM_PORT;

	public static String ZK_HOST;
	public static int ZK_CLIENT_TIMEOUT;
	public static int ZK_CONNECT_TIMEOUT;
	public static String SOLR_DEFAULT_COLLECTION;
	public static String SOLR_COLLECTION_SERVER;

	static {
		InputStream inSystem = ConfigFileUtil.class.getClassLoader()
				.getResourceAsStream("system.properties");

		try {

			propsSystem.load(inSystem);

			HBASE_ZOOKEEPER_QUORUM = propsSystem
					.getProperty("hbase.zookeeper.quorum");
			HBASE_ZOOKEEPER_QUORUM_PORT = propsSystem
					.getProperty("hbase.zookeeper.property.clientPort");


			ZK_HOST = propsSystem.getProperty("zk.host");
			ZK_CLIENT_TIMEOUT = Integer.parseInt(propsSystem
					.getProperty("zk.client.timeout"));
			ZK_CONNECT_TIMEOUT = Integer.parseInt(propsSystem
					.getProperty("zk.connect.timeout"));
			SOLR_DEFAULT_COLLECTION = propsSystem
					.getProperty("solr.default.collection");
			SOLR_COLLECTION_SERVER= propsSystem
					.getProperty("solr.collection.server");
			
			JDBC_DRIVERCLASSNAME = propsSystem
					.getProperty("jdbc.driverClassName");
			JDBC_URL = propsSystem.getProperty("jdbc.url");
			JDBC_USERNAME = propsSystem.getProperty("jdbc.username");
			JDBC_PASSWORD = propsSystem.getProperty("jdbc.password");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
	}
}
