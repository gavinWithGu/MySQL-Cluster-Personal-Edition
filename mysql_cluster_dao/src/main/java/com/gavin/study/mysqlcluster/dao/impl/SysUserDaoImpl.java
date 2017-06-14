package com.gavin.study.mysqlcluster.dao.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.gavin.study.mysqlcluster.bean.SysUser;
import com.gavin.study.mysqlcluster.dao.ISysUserDao;
import com.gavin.study.mysqlcluster.mapper.SysUserMapper;

@Repository
public class SysUserDaoImpl<T extends SysUser> implements ISysUserDao {
	
	@Resource
	private SysUserMapper<SysUser> sysUserMapper;
	
	@Override
    public List<SysUser> getSysUserList(int start, int limit, Map<String, Object> param) {
		param.put("start", start);
		param.put("limit", limit);
	    return sysUserMapper.getSysUserList(param);
    }

	@Override
    public int getSysUserCount(Map<String, Object> param) {
	    return sysUserMapper.getSysUserCount(param);
    }
	

	@Override
    public void insert(SysUser sysUser) {
		sysUserMapper.insert(sysUser);
    }

	@Override
    public void update(SysUser sysUser) {
		sysUserMapper.update(sysUser);
    }

	@Override
    public void delete(String[] ids) {
		sysUserMapper.delete(ids);
    }

	@Override
    public void enable(String[] ids, int status) {
		sysUserMapper.enable(ids, status);
    }
	
	@Override
	public SysUser login(String userName,String loginPwd) {
        return sysUserMapper.login(userName, loginPwd);
	}

	@Override
    public SysUser load(String id) {
	    return sysUserMapper.load(id);
    }

	@Override
    public int isExist(Map<String, Object> param) {
		return sysUserMapper.isExist(param);
    }

}
