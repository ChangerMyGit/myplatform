package com.oecp.myplatform.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.oecp.myplatform.common.utils.LogUtils;
import com.oecp.myplatform.common.utils.SpringUtil;
import com.oecp.myplatform.service.log.LogSerice;

/**
 * 拦截异常
 * @author Changer
 *（1）使用Spring MVC提供的简单异常处理器SimpleMappingExceptionResolver； 
 *（2）实现Spring的异常处理接口HandlerExceptionResolver 自定义自己的异常处理器； 
 *（3）使用@ExceptionHandler注解实现异常处理； 
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

	private LogSerice logSevive = (LogSerice) SpringUtil.getBean(LogSerice.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex.toString());
		LogUtils.saveLog(request, ex, logSevive);
		return new ModelAndView("error", model);
	}

}
