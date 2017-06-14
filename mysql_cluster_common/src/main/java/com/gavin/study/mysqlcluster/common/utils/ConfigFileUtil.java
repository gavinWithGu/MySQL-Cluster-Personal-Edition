package com.gavin.study.mysqlcluster.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileUtil {

	public static Properties props = new Properties();
	public static Properties propsJdbc = new Properties();
	public static Properties propsSystem = new Properties();

	public static String JDBC_DRIVERCLASSNAME;
	public static String JDBC_URL;
	public static String JDBC_USERNAME;
	public static String JDBC_PASSWORD;

	public static String SPARK_HOME;

	public static String JOBMANAGER_HOME;

	static {
		InputStream in = ConfigFileUtil.class.getClassLoader()
				.getResourceAsStream("memcached.properties");

		InputStream inJdbc = ConfigFileUtil.class.getClassLoader()
				.getResourceAsStream("jdbc.properties");

		InputStream inSystem = ConfigFileUtil.class.getClassLoader()
				.getResourceAsStream("system.properties");

		try {
			props.load(in);

			propsJdbc.load(inJdbc);
			propsSystem.load(inSystem);

			JDBC_DRIVERCLASSNAME = propsJdbc
					.getProperty("jdbc.driverClassName");
			JDBC_URL = propsJdbc.getProperty("jdbc.url");
			JDBC_USERNAME = propsJdbc.getProperty("jdbc.username");
			JDBC_PASSWORD = propsJdbc.getProperty("jdbc.password");

			SPARK_HOME = propsSystem.getProperty("spark.home");
			JOBMANAGER_HOME= propsSystem.getProperty("jobmanager.home");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// if (in != null) {
			// try {
			// in.close();
			// } catch (IOException e) {
			//
			// }
			// }

			if (inJdbc != null) {
				try {
					inJdbc.close();
				} catch (IOException e) {

				}
			}
		}
	}
}
