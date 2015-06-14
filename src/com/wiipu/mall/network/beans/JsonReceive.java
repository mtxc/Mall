package com.wiipu.mall.network.beans;

/**
 * 从服务器收到的Json数据实体类
 */
public class JsonReceive {

	/**
	 * 响应的接口名
	 */
	private String method;
	/**
	 * 响应的时间戳
	 */
	private long timestamp;
	/**
	 * 响应的状态码
	 */
	private int status;
	/**
	 * 响应的错误字段
	 */
	private String error;
	/**
	 * 响应用时
	 */
	private long times_used;
	/**
	 * 响应的实体部分
	 */
	private Object response;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public long getTimes_used() {
		return times_used;
	}

	public void setTimes_used(long times_used) {
		this.times_used = times_used;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "JsonReceive [method=" + method + ", timestamp=" + timestamp
				+ ", status=" + status + ", error=" + error + ", times_used="
				+ times_used + ", response=" + response + "]";
	}

}
