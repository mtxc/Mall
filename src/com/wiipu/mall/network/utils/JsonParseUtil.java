package com.wiipu.mall.network.utils;

import org.json.JSONObject;

/**
 * Bean类和JsonObject互相转化的工具类
 */
public class JsonParseUtil {
	
	/**
	 * 先讲request封装成JsonPost，再将JsonPost对象放入JsonObject中
	 * @param request 请求的实体类
	 * @return 封装后的JsonObject
	 */
	public static JSONObject beanParseJson(Object request){
		JSONObject json = new JSONObject();
		return json;
	}
	
}
