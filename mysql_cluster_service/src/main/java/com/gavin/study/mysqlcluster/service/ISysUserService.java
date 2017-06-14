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

package com.gavin.study.mysqlcluster.service;

import java.util.List;
import java.util.Map;

import com.gavin.study.mysqlcluster.bean.SysUser;
import com.gavin.study.mysqlcluster.common.exception.InsuranceException;

/**
 * TODO 类或接口说明
 * <p/>
 * 
 * @author <a href="mailto:xcai@isoftstone.com">Alvin Ai</a>
 * @version  Date: 2015-6-17 下午4:07:01
 * @serial 1.0
 * @since 2015-6-17 下午4:07:01
 */

public interface ISysUserService {
	
	public List<SysUser> getSysUserList(int start, int limit, Map<String, Object> param);
	
	public int getSysUserCount(Map<String, Object> param);
	
	public void insert(SysUser sysUser) throws InsuranceException;
	
	public void update(SysUser sysUser) throws InsuranceException;
	
	public void delete(String[] ids);
	
	public void enable(String[] ids, int status);
	
	public SysUser login(String loginName, String password);

}
