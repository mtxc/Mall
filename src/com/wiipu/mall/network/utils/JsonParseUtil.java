package com.wiipu.mall.network.utils;

import org.json.JSONObject;

import com.wiipu.mall.network.beans.JsonReceive;

/**
 * Bean类和JsonObject互相转化的工具类
 */
public class JsonParseUtil {
	
	/**
	 * 先将request封装成JsonPost，再将JsonPost对象放入JsonObject中
	 * @param method 请求的接口名
	 * @param request 请求的实体类
	 * @return 封装后的JsonObject
	 */
	public static JSONObject beanParseJson(String method, Object request){
		JSONObject json = new JSONObject();
		return json;
	}
	
	/**
	 * 将JsonObject对象解析为JsonReceive
	 * @param json
	 * @return
	 */
	public static JsonReceive jsonParseBean(JSONObject json){
		JsonReceive receive = new JsonReceive();
		return receive;
	}
	
}
