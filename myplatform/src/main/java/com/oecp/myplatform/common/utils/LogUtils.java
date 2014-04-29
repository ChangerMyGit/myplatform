package com.oecp.myplatform.common.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.oecp.myplatform.common.enums.LogType;
import com.oecp.myplatform.model.log.SysLog;
import com.oecp.myplatform.service.log.LogSerice;

public class LogUtils {
	// 保存日志
	public static void saveLog(HttpServletRequest request, Exception ex,
			LogSerice logSevive) {
		StringBuffer params = new StringBuffer();
		Map map = request.getParameterMap();
		SysLog log = new SysLog();
		log.setMethod(request.getMethod());
		log.setParams(JSON.toJSONString(map));
		log.setRemoteAddr(request.getRemoteAddr());
		log.setRequertUrl(request.getRequestURI());
		log.setUserAgent(request.getHeader("user_agent"));
		log.setType(ex == null ? LogType.TYPE_ACCESS : LogType.EXCEPTION);
		log.setExceptionMessage(ex != null ? ex.toString() : null);
		logSevive.create(log);
	}
}
