package com.gavin.study.mysqlcluster.bean;

import com.gavin.study.mysqlcluster.bean.base.IdEntity;

/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */

public class MasterSlaveInfo extends IdEntity {

	// Fields
	private String userName;
	private String password;
	private String schemaInfo;
	private int isMaster;
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
	public String getSchemaInfo() {
		return schemaInfo;
	}
	public void setSchemaInfo(String schemaInfo) {
		this.schemaInfo = schemaInfo;
	}
	public int getIsMaster() {
		return isMaster;
	}
	public void setIsMaster(int isMaster) {
		this.isMaster = isMaster;
	}

}