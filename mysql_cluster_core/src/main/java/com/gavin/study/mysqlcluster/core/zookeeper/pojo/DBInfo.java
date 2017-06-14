package com.gavin.study.mysqlcluster.core.zookeeper.pojo;

public class DBInfo {
	private String url;
	private String userName;
	private String password;
	private String driverClassName;
	private String sn;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public DBInfo(String url, String userName, String password,
			String driverClassName, String sn) {
		this.url = url;
		this.userName = userName;
		this.password = password;
		this.driverClassName = driverClassName;
		this.sn = sn;
	}

}
