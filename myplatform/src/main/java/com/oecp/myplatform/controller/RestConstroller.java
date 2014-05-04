package com.oecp.myplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oecp.myplatform.common.controller.BaseController;
import com.oecp.myplatform.common.service.BaseService;

@Controller
@RequestMapping("/test") // 请求路径
public class RestConstroller extends BaseController{
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET) 
	@ResponseBody
	public String registPost() throws Exception {
		return "welcome to json";
	}

	@Override
	protected BaseService getService() {
		// TODO Auto-generated method stub
		return null;
	}

}
