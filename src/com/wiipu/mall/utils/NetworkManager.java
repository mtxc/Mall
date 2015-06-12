package com.wiipu.mall.utils;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.wiipu.mall.R;

/**
 * 网络请求操作单例类
 */
public class NetworkManager {
	
	private RequestQueue mQueue;

	/**
	 * 静态内部类的方法实现单例模式
	 */
	private static class Holder{
		private static final NetworkManager INSTANCE = new NetworkManager();
	}
	
	/**
	 * 获取发送器单例
	 */
	public static NetworkManager getInstance(){
		return Holder.INSTANCE;
	}
	
	/**
	 * 初始化
	 * @param context 上下文
	 */
	public void init(Context context){
		mQueue = Volley.newRequestQueue(context);
	}
	
	/**
	 * 设置NetworkImageView的资源异步加载
	 * @param iv 要设置的NetworkImageView
	 * @param url 资源的链接
	 */
	public void setImageUrl(NetworkImageView iv, String url){
		if(mQueue != null){
			//设置默认图片资源
			iv.setDefaultImageResId(R.drawable.default_image_loading);
			//设置网络错误图片资源
			iv.setErrorImageResId(R.drawable.default_image_error);
			//设置网络图片资源
			iv.setImageUrl(url, new ImageLoader(mQueue, new BitmapLruCache()));
		}else{
			LogUtil.log(LogType.ERROR, getClass(), "RequestQuest未初始化");
		}
	}
	
}
