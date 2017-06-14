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

package com.gavin.study.mysqlcluster.portal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gavin.study.mysqlcluster.bean.SysUser;
import com.gavin.study.mysqlcluster.common.exception.InsuranceException;
import com.gavin.study.mysqlcluster.common.thread.DBContextHolder;
import com.gavin.study.mysqlcluster.common.utils.CommonUtils;
import com.gavin.study.mysqlcluster.common.utils.EncryptionUtils;
import com.gavin.study.mysqlcluster.dto.SysUserDto;
import com.gavin.study.mysqlcluster.service.ISysUserService;

/**
 * TODO 类或接口说明
 * <p/>
 * 
 * @author <a href="mailto:xcai@isoftstone.com">Alvin Ai</a>
 * @version  Date: 2015-6-17 下午4:15:36
 * @serial 1.0
 * @since 2015-6-17 下午4:15:36
 */
@RequestMapping("/sysUser")
@Controller
public class SysUserController extends BaseController{
	
	@Resource
	private ISysUserService sysUserService;
	
	
	/**
	 * 查询系统用户列表
	 * <p/>
	 * @author Alvin Ai
	 * @date 2015-6-17 下午4:20:56
	 * @param name
	 * @param mobilePhone
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping("/getSysUserList.do")
	public @ResponseBody JSONObject getSysUserList(
			@RequestParam(value = "searchLoginName", required = false) String loginName,
			@RequestParam(value = "searchName", required = false) String name,
			@RequestParam(value = "searchWorkNumber", required = false) String workNumber,
			@RequestParam(value = "searchMobilePhone", required = false) String mobilePhone,
			@RequestParam(value = "sEcho", required = false) Integer sEcho,
			@RequestParam(value = "iDisplayStart", required = false, defaultValue = "0") Integer start,
			@RequestParam(value = "iDisplayLength", required = false, defaultValue = "10") Integer limit) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			if (CommonUtils.isNotEmpty(loginName)) {
				param.put("loginName", loginName);
			}
			if (CommonUtils.isNotEmpty(name)) {
				param.put("name", name);
			}
			if (CommonUtils.isNotEmpty(workNumber)) {
				param.put("workNumber", workNumber);
			}
			if (CommonUtils.isNotEmpty(mobilePhone)) {
				param.put("mobilePhone", mobilePhone);
			}
			List<SysUser> list = sysUserService.getSysUserList(start, limit, param);
			List<SysUserDto> data = new ArrayList<SysUserDto>();
			for (SysUser sysUser : list) {
		        data.add(SysUserDto.convert(sysUser));
	        }
			
			int count = sysUserService.getSysUserCount(param);
			return this.toJSONResult(count,data,sEcho);
		}catch (Exception e) {
			return this.toJSONResult(false, e.getMessage());
		}
		
	}
	
	@RequestMapping("/insert")
	public @ResponseBody JSONObject insert(SysUser sysUser){
		try {
			if (CommonUtils.isEmpty(sysUser.getDefaulted())) {
				sysUser.setDefaulted("0");
			}
			sysUser.setPassword(EncryptionUtils.encryptBasedMd5("123456"));
			//DBContextHolder.setDBType("1");
			sysUserService.insert(sysUser);
			return this.toJSONResult(true);
		}catch (InsuranceException e) {
			return this.toJSONResult(false, e.getErrorMsg());
		}
	}
	
	@RequestMapping("/update")
	public @ResponseBody JSONObject update(SysUser sysUser){
		try {
			if (CommonUtils.isEmpty(sysUser.getDefaulted())) {
				sysUser.setDefaulted("0");
			}
			//DBContextHolder.setDBType("1");
	        sysUserService.update(sysUser);
	        return this.toJSONResult(true);
        } catch (InsuranceException e) {
        	return this.toJSONResult(false, e.getErrorMsg());
        }
	}
	
	@RequestMapping("/delete")
	public @ResponseBody JSONObject delete(String ids){
		try {
			if(CommonUtils.isNotEmpty(ids)){
				sysUserService.delete(ids.split(","));
			}
			//DBContextHolder.setDBType("1");
			return this.toJSONResult(true);
		} catch (Exception e) {
			return this.toJSONResult(false, e.getMessage());
		}
	}

}
