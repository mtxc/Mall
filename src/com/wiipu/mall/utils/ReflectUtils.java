package com.wiipu.mall.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射相关的工具类
 * */
public class ReflectUtils {

	/**
	 * @param method
	 *            :要判断的方法的method对象
	 * 
	 *            判断某个方法是否是get方法
	 * */
	public static boolean isGetterMethod(Method method) {
		String name = method.getName();
		if (name.startsWith("get"))
			return true;
		return false;
	}

	/**
	 * 
	 * @param m
	 *            :需要判断的方法的method对象 得到get或set方法的名字
	 * */
	public static String getName(Method m) {
		StringBuffer sb = new StringBuffer(m.getName());
		sb.delete(0, 3);
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 * 获取get方法的返回值
	 * 
	 * @return: 返回灌装了数据后的object
	 */
	public static Object getValue(Method m, Object obj) {
		Object o = null;
		try {
			o = m.invoke(obj, (Object[]) null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return o;
	}
}
