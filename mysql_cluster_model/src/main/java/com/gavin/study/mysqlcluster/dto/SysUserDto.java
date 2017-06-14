/**
 * Copyright 2013-2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */	  

package com.gavin.study.mysqlcluster.dto;

import com.gavin.study.mysqlcluster.bean.SysUser;
import com.gavin.study.mysqlcluster.common.utils.CommonUtils;
import com.gavin.study.mysqlcluster.common.utils.CopyUtils;
import com.gavin.study.mysqlcluster.common.utils.DateUtils;

/**
 * TODO 类或接口说明
 * <p/>
 * 
 * @author <a href="mailto:xcai@isoftstone.com">Alvin Ai</a>
 * @version  Date: 2015-6-17 下午4:22:44
 * @serial 1.0
 * @since 2015-6-17 下午4:22:44
 */

public class SysUserDto {
	
	private String id;
	private String loginName;
	private String password;
	private String workNumber;
	private String name;
	private String email;
	private String mobilePhone;
	private String status;
	private String defaulted;
	private String createdTime;
	private String deletedTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
    public String getCreatedTime() {
    	return createdTime;
    }
	/**
     * @param createdTime the createdTime to set
     */
    public void setCreatedTime(String createdTime) {
    	this.createdTime = createdTime;
    }
	/**
     * @return the deletedTime
     */
    public String getDeletedTime() {
    	return deletedTime;
    }
	/**
     * @param deletedTime the deletedTime to set
     */
    public void setDeletedTime(String deletedTime) {
    	this.deletedTime = deletedTime;
    }
    
    public static SysUserDto convert(SysUser sysUser) throws Exception{
    	SysUserDto sysUserDto = new SysUserDto();
    	CopyUtils.copyProperty(sysUserDto, sysUser);
    	if(CommonUtils.isNotEmpty(sysUser.getCreatedTime())){
    		sysUserDto.setCreatedTime(DateUtils.time2String(sysUser.getCreatedTime()));
    	}
    	if(CommonUtils.isNotEmpty(sysUser.getDeletedTime())){
    		sysUserDto.setDeletedTime(DateUtils.time2String(sysUser.getDeletedTime()));
    	}
    	return sysUserDto;
    }
}
