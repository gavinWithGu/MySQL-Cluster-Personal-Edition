package com.gavin.study.zookeeper.stub.utils;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * json工具类
 * 
 * @author GuGuangyin
 * 
 */
public class JsonUtils {

	public static <T> T getObj(String jsonString, Class<T> cls) {
		T t = null;
		Gson gson = new Gson();
		t = gson.fromJson(jsonString, cls);
		return t;
	}

	/**
	 * 将map的内容转换成json
	 * 
	 * @param map
	 * @return
	 */
	public static String getJsonContentFromMap(Map<String, String> map) {
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		Gson gson = new Gson();

		String jsonContent = gson.toJson(map, type);
		return jsonContent;
	}

	/**
	 * 将map的内容转换成json
	 * 
	 * @param map
	 * @return
	 */
	public static String getJsonContentFromObjMap(Map<String, Object> map) {
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		Gson gson = new Gson();

		String jsonContent = gson.toJson(map, type);
		return jsonContent;
	}

	/**
	 * 将json内容转换成map
	 * 
	 * @param jsonContent
	 * @return
	 */
	public static Map<String, String> getMapFromJson(String jsonContent) {
		// 调用第三方jar包，解析JSON内容
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		Gson gson = new Gson();
		Map<String, String> map = gson.fromJson(jsonContent, type);
		return map;
	}

	/**
	 * 将json内容转换成map复杂对象
	 * 
	 * @param jsonContent
	 * @return
	 */
	public static Map<String, Object> getMapObjFromJson(String jsonContent) {
		// 调用第三方jar包，解析JSON内容
		Type type = new TypeToken<Map<String, Object>>() {
		}.getType();
		Gson gson = new Gson();
		Map<String, Object> map = gson.fromJson(jsonContent, type);
		return map;
	}
}
