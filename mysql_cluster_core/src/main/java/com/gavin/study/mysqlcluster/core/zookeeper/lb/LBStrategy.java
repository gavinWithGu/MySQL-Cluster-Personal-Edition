package com.gavin.study.mysqlcluster.core.zookeeper.lb;


public interface LBStrategy {
	
	public static final int RANDOM = 1;
	public static final int ROUND_ROBIN = 2;
	
	public Object pickup();
}
