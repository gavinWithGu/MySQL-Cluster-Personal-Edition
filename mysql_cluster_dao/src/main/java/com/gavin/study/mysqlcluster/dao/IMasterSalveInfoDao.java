package com.gavin.study.mysqlcluster.dao;

import java.util.List;
import java.util.Map;

import com.gavin.study.mysqlcluster.bean.MasterSlaveInfo;

public interface IMasterSalveInfoDao {
	public List<MasterSlaveInfo> getList(Map<String, Object> param);
}
