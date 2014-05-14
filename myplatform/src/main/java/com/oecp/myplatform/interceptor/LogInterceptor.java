package com.oecp.myplatform.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.oecp.myplatform.common.utils.LogUtils;
import com.oecp.myplatform.common.utils.SpringUtil;
import com.oecp.myplatform.service.log.LogService;

/**
 * 系统日志拦截器
 * 
 * @author changer
 * @date 2014-04-29
 */
public class LogInterceptor implements HandlerInterceptor {

	private LogService logSevive = (LogService) SpringUtil.getBean(LogService.class);

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String requestUrl = request.getRequestURI();
		if (StringUtils.endsWith(requestUrl, "/save")
				|| StringUtils.endsWith(requestUrl, "/delete") || ex != null) {
			LogUtils.saveLog(request, ex, logSevive);
		}
		//System.out.println("afterCompletion");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		//System.out.println("postHandle");
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		//System.out.println("preHandle");
		return true;
	}

}
