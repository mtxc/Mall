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
import com.wiipu.mall.network.beans.ErrorHook;
import com.wiipu.mall.network.beans.ResponseHook;
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

	/**
	 * 向服务器发送请求
	 * 
	 * @param method
	 *            请求的接口名
	 * @param request
	 *            请求的实体
	 * @param responseHook
	 *            响应成功的接口class
	 * @param errorHook
	 *            响应失败的接口class
	 * @param responses
	 *            响应的实体类class数组
	 */
	public void post(final String method, final Object request,
			final Class<? extends ResponseHook> responseHook,
			final Class<? extends ErrorHook> errorHook, final Class<?>... responses) {
		new Thread() {
			@Override
			public void run() {
				if (mQueue != null) {
					// 将request响应实体加上公共部分，转化成JSONObject
					JSONObject jsonRequest = JsonParseUtil.beanParseJson(
							method, request, mContext);
					LogUtil.log(LogType.DEBUG, getClass(), "请求封装完成："
							+ jsonRequest.toString());
					JsonObjectRequest req = new JsonObjectRequest(Method.POST,
							Constants.URL, jsonRequest,
							new Listener<JSONObject>() {

								@Override
								public void onResponse(JSONObject json) {
									// 请求成功响应，将JSONObject转换成JsonReceive
									LogUtil.log(LogType.DEBUG, getClass(),
											"请求成功：" + json.toString());
									if (responseHook != null) {
										try {
											responseHook.newInstance().deal(
													mContext,
													JsonParseUtil
															.jsonParseBean(
																	json,
																	responses));
										} catch (InstantiationException e) {
											e.printStackTrace();
										} catch (IllegalAccessException e) {
											e.printStackTrace();
										}
									}
								}
							}, new ErrorListener() {

								@Override
								public void onErrorResponse(VolleyError error) {
									// 请求失败
									LogUtil.log(LogType.ERROR, getClass(),
											"请求失败");
									if (errorHook != null) {
										try {
											errorHook.newInstance().deal(
													mContext, error);
										} catch (InstantiationException e) {
											e.printStackTrace();
										} catch (IllegalAccessException e) {
											e.printStackTrace();
										}
									}
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
