package com.gavin.study.mysqlcluster.mapper;

import java.util.List;
import java.util.Map;

import com.gavin.study.mysqlcluster.bean.MasterSlaveInfo;
import com.gavin.study.mysqlcluster.common.base.mapper.SqlMapper;


public interface MasterSlaveInfoMapper<T extends MasterSlaveInfo> extends SqlMapper<T> {
	
	public List<MasterSlaveInfo> getList(Map<String, Object> param);
}
