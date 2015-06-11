package com.wiipu.mall.utils;

import android.util.Log;

/**
 * 打印Log的工具
 */
public class LogUtil {
	
	/**
	 * 打印Log的标签
	 */
	private static final String TAG = "MallLog";
	
	/**
	 * 打印Log的最低等级
	 * LEVEL=1 打印debug、warning、error
	 * LEVEL=2 打印warning、error
	 * LEVEL=3 打印error
	 * LEVEL>3 不打印
	 */
	private static final int LEVEL = 1;
	
    public static void log(LogType type, Class<?> clazz, String info){
    	if(LEVEL > type.level)
    		return;
        switch (type){
            case DEBUG:
                Log.d(TAG, "[DEBUG]" + clazz.getSimpleName() + ":" + info);
                break;
            case WARNING:
                Log.w(TAG, "[WARNING]" + clazz.getSimpleName() + ":" + info);
                break;
            case ERROR:
                Log.e(TAG, "[ERROR]" + clazz.getSimpleName() + ":" + info);
                break;
        }
    }
}
