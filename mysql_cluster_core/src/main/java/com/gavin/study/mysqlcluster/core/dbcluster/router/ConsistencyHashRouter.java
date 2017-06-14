package com.gavin.study.mysqlcluster.core.dbcluster.router;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistencyHashRouter {
	private TreeMap<Long, Object> nodes = null;
	// 真实服务器节点信息
	private List<String> shards = new ArrayList<String>();
	// 设置虚拟节点数目
	private int VIRTUAL_NUM =2;

	/**
	 * 初始化一致环
	 */
	public void init() {
		shards.add("sn1");
		shards.add("sn2");
		shards.add("sn3");
		shards.add("sn4");
		shards.add("sn5");
		shards.add("sn6");

		nodes = new TreeMap<Long, Object>();
		for (int i = 0; i < shards.size(); i++) {
			String shardInfo = shards.get(i);
			for (int j = 0; j < VIRTUAL_NUM; j++) {
				nodes.put(hash(computeMd5("SHARD-" + i + "-NODE-" + j), j),
						shardInfo);
			}
		}
	}

	/**
	 * 根据key的hash值取得服务器节点信息
	 * 
	 * @param hash
	 * @return
	 */
	public Object getShardInfo(long hash) {
		SortedMap<Long, Object> tailMap = nodes.tailMap(hash);
		if (tailMap.isEmpty()) {
			hash = nodes.firstKey();
		} else {
			hash = tailMap.firstKey();
		}
		return nodes.get(hash);
	}

	/**
	 * 打印圆环节点数据
	 */
	public void printMap() {
		System.out.println(nodes);
	}

	/**
	 * 根据2^32把节点分布到圆环上面。
	 * 
	 * @param digest
	 * @param nTime
	 * @return
	 */
	public long hash(byte[] digest, int nTime) {
		long rv = ((long) (digest[3 + nTime * 4] & 0xFF) << 24)
				| ((long) (digest[2 + nTime * 4] & 0xFF) << 16)
				| ((long) (digest[1 + nTime * 4] & 0xFF) << 8)
				| (digest[0 + nTime * 4] & 0xFF);

		return rv & 0xffffffffL; /* Truncate to 32-bits */
	}

	/**
	 * Get the md5 of the given key. 计算MD5值
	 */
	public byte[] computeMd5(String k) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 not supported", e);
		}
		md5.reset();
		byte[] keyBytes = null;
		try {
			keyBytes = k.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unknown string :" + k, e);
		}

		md5.update(keyBytes);
		return md5.digest();
	}

	public static void main(String[] args) {
		Random ran = new Random();
		ConsistencyHashRouter hash = new ConsistencyHashRouter();
		hash.init();
		hash.printMap();

		System.out.println(hash.getShardInfo(hash.hash(
				hash.computeMd5("118080814ab7eac2014ab7fa6155000f"),
				ran.nextInt(hash.VIRTUAL_NUM))));
		
		System.out.println(hash.getShardInfo(hash.hash(
				hash.computeMd5("118080814ab7eac2014ab7fa6155000f"),
				ran.nextInt(hash.VIRTUAL_NUM))));
	}
}
