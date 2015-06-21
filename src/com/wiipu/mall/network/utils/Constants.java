package com.wiipu.mall.network.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 网络请求常量类
 */
public class Constants {

	/**
	 * 设备的sn码
	 * */
	private static String sn = "";
	/**
	 * 网络请求的url
	 */
	public static final String URL = "http://192.168.0.104:8080/VolleyTest/servlet/TestServlet";
	public static final String APPKEY = "888";
	public static final int RTIMES = 1;
	public static final String SECRET = "567745674567544";

	/**
	 * 获取设备的sn码
	 * */
	public static String getSN(Context context) {
		if (sn.equals("")) {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			sn = tm.getSimSerialNumber();
		}
		return sn;
	}
}
