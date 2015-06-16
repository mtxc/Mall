package com.wiipu.mall.network.utils;

import java.lang.reflect.Field;

/**
 * 反射的工具类
 */
public class ReflectUtil {

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
