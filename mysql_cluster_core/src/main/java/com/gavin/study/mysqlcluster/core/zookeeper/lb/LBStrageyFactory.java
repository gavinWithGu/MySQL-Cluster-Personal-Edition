package com.gavin.study.mysqlcluster.core.zookeeper.lb;

import java.util.List;

import com.gavin.study.mysqlcluster.core.zookeeper.lb.impl.LBRandomStrategy;
import com.gavin.study.mysqlcluster.core.zookeeper.lb.impl.LBRoundRobinStrategy;

public class LBStrageyFactory {
	private static final LBStrageyFactory instance = new LBStrageyFactory();
	private LBStrageyFactory() {
	}

	public static LBStrageyFactory getInstance() {
		return instance;
	}

	public LBStrategy getLBStrategy(int type,List<?> repos) {
		switch (type) {
		case LBStrategy.RANDOM:
			return new LBRandomStrategy(repos);
		default:
			return new LBRoundRobinStrategy(repos);
		}
	}
}
