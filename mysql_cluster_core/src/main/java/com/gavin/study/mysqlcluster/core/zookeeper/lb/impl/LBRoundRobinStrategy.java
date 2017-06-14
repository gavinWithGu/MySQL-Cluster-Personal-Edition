package com.gavin.study.mysqlcluster.core.zookeeper.lb.impl;

import java.util.List;

import com.gavin.study.mysqlcluster.core.zookeeper.lb.LBStrategy;
import com.gavin.study.mysqlcluster.utils.RoundRobinUtils;

public class LBRoundRobinStrategy implements LBStrategy {

	private List<?> repos;

	public LBRoundRobinStrategy(List<?> repos) {
		this.repos = repos;
	}

	@Override
	public Object pickup() {
		return repos.get(RoundRobinUtils.next(repos.size()));
	}
}