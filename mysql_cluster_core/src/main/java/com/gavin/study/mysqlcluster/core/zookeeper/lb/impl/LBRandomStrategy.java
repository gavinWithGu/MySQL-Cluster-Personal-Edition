package com.gavin.study.mysqlcluster.core.zookeeper.lb.impl;

import java.util.List;

import com.gavin.study.mysqlcluster.core.zookeeper.lb.LBStrategy;
import com.gavin.study.mysqlcluster.utils.RandomGeneratorUtils;

public class LBRandomStrategy implements LBStrategy {

	private List<?> repos;

	public LBRandomStrategy(List<?> repos) {
		this.repos = repos;
	}

	@Override
	public Object pickup() {
		return repos.get(RandomGeneratorUtils.getRandomNumber(repos.size()));
	}
}
