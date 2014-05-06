package com.oecp.myplatform.common.controller;

import java.io.Serializable;

/**
 * 将返回的Json统一格式 
 * @author Changer
 *
 */
public class JsonResult implements Serializable {

	private static final long serialVersionUID = -4059094622427629995L;

	private boolean success;
	
	private String msg;
	
	private Object result;

    public JsonResult(){}
	
	public JsonResult(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public JsonResult(Object result){
		this.success = true;
		this.result = result;
		this.msg = "执行成功！";
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
}
