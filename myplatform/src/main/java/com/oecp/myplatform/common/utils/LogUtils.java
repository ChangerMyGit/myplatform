package com.oecp.myplatform.common.utils;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.oecp.myplatform.common.enums.LogType;
import com.oecp.myplatform.model.log.SysLog;
import com.oecp.myplatform.service.log.LogService;

public class LogUtils {
	// 保存日志
	public static void saveLog(HttpServletRequest request, Exception ex,
			LogService logSevive) {
		StringBuffer params = new StringBuffer();
		Date date = new Date();
		Map map = request.getParameterMap();
		SysLog log = new SysLog();
		log.setMethod(request.getMethod());
		log.setParams(JSON.toJSONString(map));
		log.setRemoteAddr(request.getRemoteAddr());
		log.setRequertUrl(request.getRequestURI());
		log.setUserAgent(request.getHeader("user_agent"));
		log.setType(ex == null ? LogType.TYPE_ACCESS : LogType.EXCEPTION);
		log.setExceptionMessage(ex != null ? ex.toString() : null);
		log.setCreated(date);
		log.setUpdated(date);
		log.setCreater("System");
		log.setUpdater("Syetem");
		logSevive.create(log);
	}
}
