package com.wiipu.mall.network.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.TreeMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.wiipu.mall.network.beans.JsonPost;
import com.wiipu.mall.network.beans.JsonReceive;
import com.wiipu.mall.network.beans.ResponseHook;
import com.wiipu.mall.utils.ReflectUtils;

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
		post.setSign(generateSign(request));

		Field[] fields = post.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				Method m = post.getClass().getMethod(
						"get"
								+ Character.toUpperCase(field.getName().charAt(
										0)) + field.getName().substring(1));
				Object object = m.invoke(post, m);
				json.put(field.getName(), object);
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
	 * @param json
	 * @return
	 */
	public static JsonReceive jsonParseBean(JSONObject json) {
		JsonReceive receive = new JsonReceive();
		return receive;
	}

	/**
	 * 生成sign签名
	 * */
	private static String generateSign(Object request) {
		// 声明一棵树来存储所有的字符串
		TreeMap<String, Object> map = new TreeMap<String, Object>();
		// 得到post中的所有方法的数组
		/* Method[] methods = post.getClass().getDeclaredMethods(); */
		Method[] methods2 = request.getClass().getDeclaredMethods();

		// 将post中除了request和sign之外所有属性放入map中
		/*
		 * for (Method m : methods) { if (ReflectUtils.isGetterMethod(m) &&
		 * isNeed(m)) { map.put(ReflectUtils.getName(m),
		 * ReflectUtils.getValue(m, post)); } }
		 */

		for (Method m : methods2) {
			map.put(ReflectUtils.getName(m), ReflectUtils.getValue(m, request));
		}

		String result = map.toString().replace("{", "").replace("}", "")
				.replace(", ", "&")
				+ "&secret=" + Constants.SECRET;

		result = MD5Utils.string2MD5(result);
		return result;
	}

	/**
	 * 判断是否需要该方法
	 * */
	private static boolean isNeed(Method m) {
		String name = m.getName();
		if (name.endsWith("Sign") || name.endsWith("Request"))
			return false;
		return true;
	}
}
