package com.gavin.study.mysqlcluster.utils;


public class RoundRobinUtils {
	private static int next = 0;
	
	public static int next(int max) {
		return ++next % max;
	}
}
