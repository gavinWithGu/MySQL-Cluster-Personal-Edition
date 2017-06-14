package com.gavin.study.mysqlcluster.core.dbcluster.router;

/**
 * 根据某种自定义的hash算法来进行散列,并根据散列的值进行路由 常见的水平切分规则有: 基于范围的切分, 比如 memberId > 10000 and
 * memberId < 20000 基于模数的切分, 比如 memberId%128==1 或者 memberId%128==2 或者...
 * 基于哈希(hashing)的切分, 比如hashing(memberId)==someValue等
 */
public class MathModRouter implements IRouter {

	public int getShardNum(Object key, int totalShardNum) {
		return  Math.abs(key.hashCode() % totalShardNum);
	}

	public static void main(String[] args) {
		MathModRouter router = new MathModRouter();
		System.out.println(router.getShardNum("2c9082b75364488f01537377842c0001", 4));
		System.out.println(router.getShardNum(1001, 4));
	}
}