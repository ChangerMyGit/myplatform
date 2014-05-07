package com.oecp.myplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oecp.myplatform.common.controller.BaseController;
import com.oecp.myplatform.common.service.BaseService;
import com.oecp.myplatform.model.User;
import com.oecp.myplatform.service.UserService;

/**
 * 用户管理
 * @author ASUS
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET,produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String list(){
		List users = userService.findByCondition(" 1 = 1", null);
		return toJson(users);
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String userManage(){
		return "userManage";
	}
	
	@Override
	protected BaseService getService() {
		return userService;
	}

}
