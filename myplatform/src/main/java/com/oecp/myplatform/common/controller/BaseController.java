package com.oecp.myplatform.common.controller;

import javax.persistence.MappedSuperclass;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

/**
 * 控制器支持类
 * @author Changer
 * @version 2014-4-21
 */
@MappedSuperclass
public abstract class BaseController implements ServletContextAware {

	private ServletContext servletContext;

	protected String jsonString;
	
	protected String toJson(Object obj) {
		this.jsonString = JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd");
		return jsonString;
	}
	
	protected String toJson(Object obj,String dateFormat) {
		this.jsonString = JSON.toJSONStringWithDateFormat(obj, dateFormat);
		return jsonString;
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	/**
	 * 获取Request
	 * @return
	 */
	protected HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**
	 * 获取Session
	 * @return
	 */
	protected HttpSession getSession(){
		return getRequest().getSession();
	}

	/**
	 * @return the jsonString
	 */
	public String getJsonString() {
		return jsonString;
	}

	/**
	 * @param jsonString the jsonString to set
	 */
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	
}
