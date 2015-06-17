package com.wiipu.mall.network.utils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
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
	 * @param responses
	 *            响应的实体类class数组
	 * @return 解析出来的JsonReceive
	 */
	public static JsonReceive jsonParseBean(JSONObject json,
			Class<?>... responses) {
		JsonReceive receive = new JsonReceive();
		try {
			receive.setMethod(json.getString("method"));
			receive.setStatus(json.getInt("status"));
			receive.setTimes_used(json.getLong("times_used"));
			receive.setTimestamp(json.getLong("timestamp"));
			receive.setError(json.getString("error"));
			Object response = null;
			if(responses.length > 0){
				 response = jsonParseResponse(
							(JSONObject) json.get("response"), responses);
			}else{
				LogUtil.log(LogType.ERROR, JsonParseUtil.class, "未传入响应的Response实体类class对象");
			}
			receive.setResponse(response);
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
	public static Object jsonParseResponse(JSONObject json, Class<?>... responses) {
		Object obj = null;
		if(responses.length > 1){
			try {
				obj = responses[0].newInstance();
				for(Field field : responses[0].getDeclaredFields()){
					if(field.getType().isAssignableFrom(ArrayList.class)){
						// 如果是数组, 递归解析
						JSONArray array = json.getJSONArray(field.getName());
						Object[] objects = (Object[]) Array.newInstance(responses[1], array.length());
						for(int i=0; i<objects.length; i++){
							objects[i] = jsonParseResponse(array.getJSONObject(i), Arrays.copyOfRange(responses, 1, responses.length));
						}
						List<?> list = asList(objects);
						Method m = responses[0].getMethod(ReflectUtil.setterNameFromField(field), list.getClass());
						m.invoke(obj, list);
					}else{
						// 如果不是数组，直接调用set方法
						Method m = responses[0].getMethod(ReflectUtil.setterNameFromField(field), field.getType());
						m.invoke(obj, json.get(field.getName()));
					}
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}else{
			try {
				obj = objectMapper.readValue(json.toString(), responses[0]);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	
	/**
	 * 将对象数组转化成对应的ArrayList
	 * @param a 对象数组
	 * @return 转化后的ArrayList
	 */
	public static <T> List<T> asList(T... a) {
		List<T> list = new ArrayList<T>();
		Collections.addAll(list, a);
		return list;
	}

}
