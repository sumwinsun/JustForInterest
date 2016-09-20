package com.sumwinsun.common.dto;

/**
 * 封装Controller返回页面的数据
 * @author sy
 * @since 2016年5月26日 15:24:18
 */
public class RequestResult<T> {
	private boolean flag;
	private T data;
	private String msg;//存放异常信息
	
	public RequestResult() {}
	//成功返回值的构造
	public RequestResult(boolean flag, T data) {
		this.flag = flag;
		this.data = data;
	}
	//失败返回值的构造：false + msg
	public RequestResult(boolean flag, String msg) {
		this.flag = flag;
		this.msg = msg;
	}
	
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
