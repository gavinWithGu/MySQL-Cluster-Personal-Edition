package com.gavin.study.mysqlcluster.core.dbcluster.router;

public interface IRouter {
	int getShardNum(Object key,int totalShardNum);
}
