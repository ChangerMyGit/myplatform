package com.oecp.myplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oecp.myplatform.common.controller.BaseController;
import com.oecp.myplatform.common.service.BaseService;
import com.oecp.myplatform.model.log.SysLog;
import com.oecp.myplatform.service.log.LogService;

@Controller
@RequestMapping("/sysLog")
public class LogController extends BaseController<SysLog> {

	@Autowired
	private LogService logService;
	
	protected BaseService getService() {
		return logService;
	}

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String userManage(){
		return "sysLog";
	}
}
