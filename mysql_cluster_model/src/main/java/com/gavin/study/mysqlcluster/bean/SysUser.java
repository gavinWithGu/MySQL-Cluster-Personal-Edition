package com.gavin.study.mysqlcluster.bean;

import java.util.Date;

import com.gavin.study.mysqlcluster.bean.base.IdEntity;


/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */

public class SysUser extends IdEntity {

	// Fields

	private String loginName;
	private String password;
	private String workNumber;
	private String name;
	private String email;
	private String mobilePhone;
	private String status;
	private String defaulted;
	private Date createdTime;
	private Date deletedTime;
	
	
	/**
     * @return the loginName
     */
    public String getLoginName() {
    	return loginName;
    }
	/**
     * @param loginName the loginName to set
     */
    public void setLoginName(String loginName) {
    	this.loginName = loginName;
    }
	/**
     * @return the password
     */
    public String getPassword() {
    	return password;
    }
	/**
     * @param password the password to set
     */
    public void setPassword(String password) {
    	this.password = password;
    }
	/**
     * @return the workNumber
     */
    public String getWorkNumber() {
    	return workNumber;
    }
	/**
     * @param workNumber the workNumber to set
     */
    public void setWorkNumber(String workNumber) {
    	this.workNumber = workNumber;
    }
	/**
     * @return the name
     */
    public String getName() {
    	return name;
    }
	/**
     * @param name the name to set
     */
    public void setName(String name) {
    	this.name = name;
    }
	/**
     * @return the email
     */
    public String getEmail() {
    	return email;
    }
	/**
     * @param email the email to set
     */
    public void setEmail(String email) {
    	this.email = email;
    }
	/**
     * @return the mobilePhone
     */
    public String getMobilePhone() {
    	return mobilePhone;
    }
	/**
     * @param mobilePhone the mobilePhone to set
     */
    public void setMobilePhone(String mobilePhone) {
    	this.mobilePhone = mobilePhone;
    }
	/**
     * @return the status
     */
    public String getStatus() {
    	return status;
    }
	/**
     * @param status the status to set
     */
    public void setStatus(String status) {
    	this.status = status;
    }
	/**
     * @return the defaulted
     */
    public String getDefaulted() {
    	return defaulted;
    }
	/**
     * @param defaulted the defaulted to set
     */
    public void setDefaulted(String defaulted) {
    	this.defaulted = defaulted;
    }
	/**
     * @return the createdTime
     */
    public Date getCreatedTime() {
    	return createdTime;
    }
	/**
     * @param createdTime the createdTime to set
     */
    public void setCreatedTime(Date createdTime) {
    	this.createdTime = createdTime;
    }
	/**
     * @return the deletedTime
     */
    public Date getDeletedTime() {
    	return deletedTime;
    }
	/**
     * @param deletedTime the deletedTime to set
     */
    public void setDeletedTime(Date deletedTime) {
    	this.deletedTime = deletedTime;
    }
	
}