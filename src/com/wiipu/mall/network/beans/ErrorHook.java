package com.wiipu.mall.network.beans;

import android.content.Context;
import com.android.volley.VolleyError;

/**
 * 请求响应失败的接口
 */
public interface ErrorHook {

	/**
	 * 响应失败的接口
	 * @param context 应用程序上下文
	 * @param error 失败的原因
	 */
	public void deal(Context context, VolleyError error);
	
}
