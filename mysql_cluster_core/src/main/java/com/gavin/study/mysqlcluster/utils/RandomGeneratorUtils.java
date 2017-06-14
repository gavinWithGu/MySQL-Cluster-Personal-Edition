package com.gavin.study.mysqlcluster.utils;

import java.util.Random;

public class RandomGeneratorUtils {
	private static Random random = new Random();

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(getRandomNumber(5));
		}
	}

	public static int getRandomNumber(int range) { // length表示生成数字的长度
		int number = random.nextInt(range);
		return number;
	}
}
