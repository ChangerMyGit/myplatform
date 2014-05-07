package com.oecp.myplatform.common.controller;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.oecp.myplatform.common.model.BaseEO;
import com.oecp.myplatform.common.service.BaseService;

/**
 * 控制器支持类
 * @author Changer
 * @version 2014-4-21
 */
@MappedSuperclass
public abstract class BaseController<T extends BaseEO> implements ServletContextAware ,ApplicationContextAware {

	private ServletContext servletContext;

	private static ApplicationContext ctx;  
	
	protected abstract BaseService getService();
	
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
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String save(@ModelAttribute T entity) {
		getService().create(entity);
		return toJson(entity);
	}
	
	/**
	 * @param entityid
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam Serializable entityid) {
		getService().delete(entityid);
		return "Delete Success!";
	}
	
	/**
	 * @param entityid
	 * @return
	 */
	@RequestMapping(value = "/find", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public <T extends BaseEO> String find(@RequestParam Serializable entityid) {
		T t = getService().find(entityid);
		return toJson(t);
	}
	
	@Override  
    public void setApplicationContext(ApplicationContext applicationContext ) throws BeansException {  
        ctx = applicationContext;  
    }  
	
	/**
	 * 日期属性转换
	 * @param binder
	 
	@InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }  */
}
