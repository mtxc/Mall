package com.wiipu.mall.network.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.TreeMap;

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

	/**
	 * 先将request封装成JsonPost，再将JsonPost对象放入JsonObject中
	 * 
	 * @param method
	 *            请求的接口名
	 * @param request
	 *            请求的实体类
	 * @return 封装后的JsonObject
	 */
	public static JSONObject beanParseJson(String method, Object request,
			Context mContext) {
		JSONObject json = new JSONObject();
		// 实例化一个JsonPost,填充数据
		JsonPost post = new JsonPost();
		post.setMethod(method);
		post.setAppkey(Constants.APPKEY);
		post.setSn(Constants.getSN(mContext));
		post.setTimestamp((new Date().getTime()) / 1000);
		post.setRtimes(1);
		post.setRequest(request);
		post.setSign(generateSign(post));
		Field[] fields = post.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				Method m = post.getClass()
						.getMethod(getterNameFromField(field));
				json.put(field.getName(), m.invoke(post));
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return json;
	}

	/**
	 * 将JsonObject对象解析为JsonReceive
	 * 
	 * @param json 服务器返回的JSONObject
	 * @return 解析出来的JsonReceive
	 */
	public static JsonReceive jsonParseBean(JSONObject json) {
		JsonReceive receive = new JsonReceive();
		try {
			receive.setMethod(json.getString("method"));
			receive.setStatus(json.getInt("status"));
			receive.setTimes_used(json.getLong("times_used"));
			receive.setTimestamp(json.getLong("timastamp"));
			receive.setError(json.getString("error"));
			receive.setResponse(json.get("response"));
		} catch (JSONException e) {
			e.printStackTrace();
			LogUtil.log(LogType.ERROR, JsonParseUtil.class, "响应的json串解析错误");
		}
		return receive;
	}

	/**
	 * 生成sign签名
	 */
	private static String generateSign(JsonPost post) {
		// 声明一棵树来存储所有的字符串
		TreeMap<String, Object> map = new TreeMap<String, Object>();
		// 将JsonPost中除sign和request之外的属性都放入TreeMap中
		Field[] fields = post.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals("sign")
					|| field.getName().equals("request"))
				continue;
			try {
				Method m = post.getClass()
						.getMethod(getterNameFromField(field));
				map.put(field.getName(), m.invoke(post));
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		// 将request中的所有属性都放入TreeMap中
		fields = post.getRequest().getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				Method m = post.getRequest().getClass()
						.getMethod(getterNameFromField(field));
				map.put(field.getName(), m.invoke(post.getRequest()));
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		// 将secret追加到最后
		String result = map.toString().replace("{", "").replace("}", "")
				.replace(", ", "&")
				+ "&secret=" + Constants.SECRET;
		result = MD5Utils.string2MD5(result);
		return result;
	}

	/**
	 * 根据属性获得其get方法的方法名
	 * 
	 * @param field
	 *            属性
	 * @return 属性的get方法名
	 */
	private static String getterNameFromField(Field field) {
		return "get" + Character.toUpperCase(field.getName().charAt(0))
				+ field.getName().substring(1);
	}

}
