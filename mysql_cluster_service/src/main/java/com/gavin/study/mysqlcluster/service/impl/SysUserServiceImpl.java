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

package com.gavin.study.mysqlcluster.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gavin.study.mysqlcluster.bean.SysUser;
import com.gavin.study.mysqlcluster.common.exception.InsuranceException;
import com.gavin.study.mysqlcluster.common.thread.DBContextHolder;
import com.gavin.study.mysqlcluster.dao.ISysUserDao;
import com.gavin.study.mysqlcluster.service.ISysUserService;

/**
 * TODO 类或接口说明
 * <p/>
 * 
 * @author <a href="mailto:xcai@isoftstone.com">Alvin Ai</a>
 * @version  Date: 2015-6-17 下午4:07:57
 * @serial 1.0
 * @since 2015-6-17 下午4:07:57
 */
@Service
public class SysUserServiceImpl<T extends SysUser> implements ISysUserService {
	
	@Resource
	private ISysUserDao sysUserDao;

	/* (non-Javadoc)
	 * @see com.insurance.service.ISysUserService#getSysUserList(int, int, java.util.Map)
	 */
	@Override
	public List<SysUser> getSysUserList(int start, int limit, Map<String, Object> param) {
		//DBContextHolder.setDBType("1"); //模拟强制从主库读取数据
		return sysUserDao.getSysUserList(start, limit, param);
	}

	/* (non-Javadoc)
	 * @see com.insurance.service.ISysUserService#getSysUserCount(java.util.Map)
	 */
	@Override
	public int getSysUserCount(Map<String, Object> param) {
		return sysUserDao.getSysUserCount(param);
	}

	/* (non-Javadoc)
	 * @see com.insurance.service.ISysUserService#insert(com.insurance.bean.SysUser)
	 */
	@Override
	@Transactional
	public void insert(SysUser sysUser) throws InsuranceException{
		try {
	        Map<String, Object> param = new HashMap<String, Object>();
	        param.put("loginName", sysUser.getLoginName());
	        int count = sysUserDao.isExist(param);
	        if(count > 0){
	        	throw new InsuranceException("用户名已存在!");
	        }
	        param.clear();
	        param.put("workNumber", sysUser.getWorkNumber());
	        count = sysUserDao.isExist(param);
	        if(count > 0){
	        	throw new InsuranceException("工号已存在!");
	        }
	        param.clear();
	        param.put("email", sysUser.getEmail());
	        count = sysUserDao.isExist(param);
	        if(count > 0){
	        	throw new InsuranceException("电子邮箱已存在!");
	        }
	        param.clear();
	        param.put("mobilePhone", sysUser.getMobilePhone());
	        count = sysUserDao.isExist(param);
	        if(count > 0){
	        	throw new InsuranceException("手机号已存在!");
	        }
	        sysUserDao.insert(sysUser);
        } catch (Exception e) {
        	if(e instanceof InsuranceException){
 	        	throw (InsuranceException)e;
 	        }else{
 	        	throw new InsuranceException("发生系统异常！");
 	        }
        }
	}


	@Override
	@Transactional
    public void update(SysUser sysUser) throws InsuranceException{
		try {
	        Map<String, Object> param = new HashMap<String, Object>();
	        param.put("id", sysUser.getId());
	        param.put("workNumber", sysUser.getWorkNumber());
	        int count = sysUserDao.isExist(param);
	        if(count > 0){
	        	throw new InsuranceException("工号已存在!");
	        }
	        param.clear();
	        param.put("id", sysUser.getId());
	        param.put("email", sysUser.getEmail());
	        count = sysUserDao.isExist(param);
	        if(count > 0){
	        	throw new InsuranceException("电子邮箱已存在!");
	        }
	        param.clear();
	        param.put("id", sysUser.getId());
	        param.put("mobilePhone", sysUser.getMobilePhone());
	        count = sysUserDao.isExist(param);
	        if(count > 0){
	        	throw new InsuranceException("手机号已存在!");
	        }
	        sysUserDao.update(sysUser);
        } catch (Exception e) {
	        if(e instanceof InsuranceException){
	        	throw (InsuranceException)e;
	        }else{
	        	throw new InsuranceException("发生系统异常！");
	        }
        }
    }

	@Override
	@Transactional
    public void delete(String[] ids) {
		sysUserDao.delete(ids);
    }

	@Override
	@Transactional
    public void enable(String[] ids, int status) {
		sysUserDao.enable(ids, status);
    }
	
	/* (non-Javadoc)
	 * @see com.insurance.service.ISysUserService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public SysUser login(String loginName, String password) {
		return sysUserDao.login(loginName, password);
	}

}
