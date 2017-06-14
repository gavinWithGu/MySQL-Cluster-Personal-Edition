package com.gavin.study.mysqlcluster.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gavin.study.mysqlcluster.bean.SysUser;
import com.gavin.study.mysqlcluster.common.base.mapper.SqlMapper;


public interface SysUserMapper<T extends SysUser> extends SqlMapper<T> {
	
	public List<SysUser> getSysUserList(Map<String, Object> param);
	
	public int getSysUserCount(Map<String, Object> param);
	
	public int insert(SysUser sysUser);
	
	public void update(SysUser sysUser);
	
	public void delete(@Param(value="ids") String[] ids);
	
	public void enable(@Param(value="ids") String[] ids, @Param(value="status") int status);
	
	public SysUser login(@Param(value="loginName") String loginName, @Param(value="password") String password);
	
	public SysUser load(String id);
	
	public int isExist(Map<String, Object> param);
}
