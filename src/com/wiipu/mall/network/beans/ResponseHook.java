package com.wiipu.mall.network.beans;

import android.content.Context;

/**
 * 请求响应成功的接口
 */
public interface ResponseHook {

	/**
	 * 成功响应的处理方法
	 * @param context 应用程序上下文
	 * @param receive 接收到的数据
	 */
	public void deal(Context context, JsonReceive receive);
	
}
