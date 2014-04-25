package com.oecp.myplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oecp.myplatformcommon.web.BaseController;

@Controller
@RequestMapping("/test") // 请求路径
public class RestConstroller extends BaseController{
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET) 
	@ResponseBody
	public String registPost() throws Exception {
		return "welcome to json";
	}

}
