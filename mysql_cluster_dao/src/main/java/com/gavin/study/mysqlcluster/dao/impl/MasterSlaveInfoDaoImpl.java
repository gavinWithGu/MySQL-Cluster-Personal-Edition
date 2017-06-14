package com.gavin.study.mysqlcluster.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.gavin.study.mysqlcluster.bean.MasterSlaveInfo;
import com.gavin.study.mysqlcluster.dao.IMasterSalveInfoDao;
import com.gavin.study.mysqlcluster.mapper.MasterSlaveInfoMapper;

@Repository
public class MasterSlaveInfoDaoImpl<T extends MasterSlaveInfo> implements
		IMasterSalveInfoDao {

	@Resource
	private MasterSlaveInfoMapper<MasterSlaveInfo> mapper;

	@Override
	public List<MasterSlaveInfo> getList(Map<String, Object> param) {
		return mapper.getList(param);
	}

}
