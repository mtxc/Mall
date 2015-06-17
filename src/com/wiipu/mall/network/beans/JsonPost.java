package com.wiipu.mall.network.beans;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.TreeMap;
import com.wiipu.mall.network.utils.Constants;
import com.wiipu.mall.network.utils.MD5Utils;
import com.wiipu.mall.network.utils.ReflectUtil;
import android.content.Context;

/**
 * 向服务器发送的Json数据实体类
 */
public class JsonPost {

	/**
	 * 请求的接口名
	 */
	private String method;
	/**
	 * 应用的appkey
	 */
	private String appkey;
	/**
	 * 设备的sn号
	 */
	private String sn;
	/**
	 * 请求的时间戳
	 */
	private long timestamp;
	/**
	 * 请求的次数
	 */
	private int rtimes;
	/**
	 * 请求的MD5签名
	 */
	private String sign;
	/**
	 * 请求的实体部分
	 */
	private Object request;

	public JsonPost() {
	}

	public JsonPost(String method, Context context, Object request) {
		this.method = method;
		this.appkey = Constants.APPKEY;
		this.sn = Constants.getSN(context);
		this.timestamp = new Date().getTime();
		this.rtimes = Constants.RTIMES;
		this.request = request;
		this.sign = generateSign();
	}

	/**
	 * 生成sign签名
	 */
	private String generateSign() {
		// 声明一棵树来存储所有的字符串
		TreeMap<String, Object> map = new TreeMap<String, Object>();
		// 将JsonPost中除sign和request之外的属性都放入TreeMap中
		map.put("method", method);
		map.put("appkey", appkey);
		map.put("sn", sn);
		map.put("timestamp", timestamp);
		map.put("rtimes", rtimes);
		if(request != null){
			// 将request中的所有属性都放入TreeMap中
			Field[] fields = request.getClass().getDeclaredFields();
			for (Field field : fields) {
				try {
					Method m = request.getClass().getMethod(
							ReflectUtil.getterNameFromField(field));
					map.put(field.getName(), m.invoke(request));
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
		}
		// 将secret追加到最后
		String result = map.toString().replace("{", "").replace("}", "")
				.replace(", ", "&")
				+ "&secret=" + Constants.SECRET;
		result = MD5Utils.string2MD5(result);
		return result;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getRtimes() {
		return rtimes;
	}

	public void setRtimes(int rtimes) {
		this.rtimes = rtimes;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Object getRequest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "JsonPost [method=" + method + ", appkey=" + appkey + ", sn="
				+ sn + ", timestamp=" + timestamp + ", rtimes=" + rtimes
				+ ", sign=" + sign + ", request=" + request + "]";
	}

}
