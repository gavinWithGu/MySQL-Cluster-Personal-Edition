package com.gavin.study.mysqlcluster.dao;

import java.util.List;
import java.util.Map;

import com.gavin.study.mysqlcluster.bean.SysUser;


public interface ISysUserDao {
	
	public List<SysUser> getSysUserList(int start, int limit, Map<String, Object> param);
	
	public int getSysUserCount(Map<String, Object> param);
	
	public void insert(SysUser sysUser);
	
	public void update(SysUser sysUser);
	
	public void delete(String[] ids);
	
	public void enable(String[] ids, int status);
	
	public SysUser login(String loginName, String password);
	
	public SysUser load(String id);
	
	public int isExist(Map<String, Object> param);
	
}
