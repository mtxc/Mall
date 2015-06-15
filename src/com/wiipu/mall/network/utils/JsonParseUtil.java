package com.wiipu.mall.network.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import com.wiipu.mall.network.beans.JsonPost;
import com.wiipu.mall.network.beans.JsonReceive;
import com.wiipu.mall.utils.LogType;
import com.wiipu.mall.utils.LogUtil;

/**
 * Bean类和JsonObject互相转化的工具类
 */
public class JsonParseUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 先将request封装成JsonPost，再将JsonPost对象放入JsonObject中
	 * 
	 * @param method
	 *            请求的接口名
	 * @param request
	 *            请求的实体类
	 * @param mContext
	 *            应用的上下文
	 * @return 封装后的JsonObject
	 */
	public static JSONObject beanParseJson(String method, Object request,
			Context mContext) {
		// 实例化一个JsonPost,填充数据
		JsonPost post = new JsonPost(method, mContext, request);
		// 将JsonPost中的所有元素放入json中
		JSONObject json = null;
		try {
			String str = objectMapper.writeValueAsString(post);
			json = new JSONObject(str);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 将JsonObject对象解析为JsonReceive
	 * 
	 * @param json
	 *            服务器返回的JSONObject
	 * @param response
	 *            响应的实体类class
	 * @return 解析出来的JsonReceive
	 */
	public static <T> JsonReceive jsonParseBean(JSONObject json,
			Class<T> response) {
		JsonReceive receive = new JsonReceive();
		try {
			receive.setMethod(json.getString("method"));
			receive.setStatus(json.getInt("status"));
			receive.setTimes_used(json.getLong("times_used"));
			receive.setTimestamp(json.getLong("timestamp"));
			receive.setError(json.getString("error"));
			receive.setResponse(jsonParseResponse(
					(JSONObject) json.get("response"), response));
		} catch (JSONException e) {
			e.printStackTrace();
			LogUtil.log(LogType.ERROR, JsonParseUtil.class, "响应的json串解析错误");
		}
		return receive;
	}

	/**
	 * 将JsonObject递归放入response中
	 * 
	 * @param json
	 *            要解析的JSONObject
	 * @param responses
	 *            响应的实体类class数组
	 * @return 解析好的response
	 */
	private static <T> T jsonParseResponse(JSONObject json, Class<T> response) {
		T t = null;
		try {
			t = objectMapper.readValue(json.toString(), response);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 根据属性获得其get方法的方法名
	 * 
	 * @param field
	 *            属性
	 * @return 属性的get方法名
	 */
	public static String getterNameFromField(Field field) {
		return "get" + Character.toUpperCase(field.getName().charAt(0))
				+ field.getName().substring(1);
	}

	/**
	 * 根据属性获得其set方法的方法名
	 * 
	 * @param field
	 *            属性
	 * @return 属性的set方法名
	 */
	public static String setterNameFromField(Field field) {
		return "set" + Character.toUpperCase(field.getName().charAt(0))
				+ field.getName().substring(1);
	}

}
