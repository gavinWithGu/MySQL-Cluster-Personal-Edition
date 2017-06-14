package com.gavin.study.mysqlcluster.common.thread;

public class DBContextHolder {

	//默认为从库
	private static String sn = "0";

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDBType(String masterFlag) {
		contextHolder.set(masterFlag);
	}

	public static String getDBType() {
		return contextHolder.get() != null ? (String) contextHolder.get() : sn;
	}

	public static void clearDBType() {
		contextHolder.remove();
	}
}
