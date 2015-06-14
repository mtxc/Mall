package com.wiipu.mall.network;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.wiipu.mall.R;
import com.wiipu.mall.network.utils.Constants;
import com.wiipu.mall.network.utils.JsonParseUtil;
import com.wiipu.mall.utils.LogType;
import com.wiipu.mall.utils.LogUtil;

/**
 * <b>网络请求操作单例类</b>
 * 
 * <br/>
 * 使用getInstance方法获得单例类 <br/>
 * 在程序发送网络请求之前需调用init方法 <br/>
 * 调用post方法发送网络请求 <br/>
 * 在程序退出时需调用remove方法
 */
public class NetworkManager {

	private Context mContext;

	/**
	 * 网络请求队列
	 */
	private RequestQueue mQueue;

	/**
	 * 静态内部类的方法实现单例模式
	 */
	private static class Holder {
		private static final NetworkManager INSTANCE = new NetworkManager();
	}

	/**
	 * 获取发送器单例
	 */
	public static NetworkManager getInstance() {
		return Holder.INSTANCE;
	}

	/**
	 * 初始化
	 * 
	 * @param context
	 *            上下文
	 */
	public void init(Context context) {
		mContext = context;
		mQueue = Volley.newRequestQueue(mContext.getApplicationContext());
	}

	public void post(String method, final Object request) {
		new Thread() {
			@Override
			public void run() {
				if (mQueue != null) {
					JSONObject jsonRequest = JsonParseUtil
							.beanParseJson(request);
					JsonObjectRequest req = new JsonObjectRequest(Method.POST,
							Constants.URL, jsonRequest,
							new Listener<JSONObject>() {

								@Override
								public void onResponse(JSONObject response) {
									// 请求成功响应
								}
							}, new ErrorListener() {

								@Override
								public void onErrorResponse(VolleyError error) {
									// 请求错误
								}
							});
					// 添加到请求队列
					mQueue.add(req);
				} else {
					LogUtil.log(LogType.ERROR, getClass(), "RequestQueue未初始化");
				}
			}
		}.start();
	}

	/**
	 * 移除所有的请求
	 */
	public void remove() {
		if (mQueue != null) {
			mQueue.cancelAll(mContext.getApplicationContext());
		} else {
			LogUtil.log(LogType.ERROR, getClass(), "RequestQueue未初始化");
		}
	}

	/**
	 * 设置NetworkImageView的资源异步加载
	 * 
	 * @param iv
	 *            要设置的NetworkImageView
	 * @param url
	 *            资源的链接
	 */
	public void setImageUrl(NetworkImageView iv, String url) {
		if (mQueue != null) {
			// 设置默认图片资源
			iv.setDefaultImageResId(R.drawable.default_image_loading);
			// 设置网络错误图片资源
			iv.setErrorImageResId(R.drawable.default_image_error);
			// 设置网络图片资源
			iv.setImageUrl(url, new ImageLoader(mQueue, new BitmapLruCache()));
		} else {
			LogUtil.log(LogType.ERROR, getClass(), "RequestQueue未初始化");
		}
	}

	/**
	 * 获取请求队列
	 * 
	 * @return 请求队列
	 */
	public RequestQueue getRequestQueue() {
		return mQueue;
	}

}
