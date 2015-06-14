package com.wiipu.mall.network.beans;

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
