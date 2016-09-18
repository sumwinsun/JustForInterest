package com.sumwinsun.common.dto;

/**
 * 封装Controller返回页面的数据
 * @author sy
 * @since 2016年5月26日 15:24:18
 */
public class RequestResult<T> {
	private boolean success;
	private T data;
	private String error;//存放异常信息
	
	public RequestResult() {}
	//成功返回值的构造
	public RequestResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}
	//失败返回值的构造：false + error
	public RequestResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
