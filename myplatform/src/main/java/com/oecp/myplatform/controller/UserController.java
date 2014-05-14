package com.oecp.myplatform.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oecp.myplatform.common.controller.BaseController;
import com.oecp.myplatform.common.controller.JsonResult;
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
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String userManage(){
		return "userManage";
	}
	
	@Override
	protected BaseService getService() {
		return userService;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String save(@ModelAttribute User user) {
		Date now = new Date();
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		user.setCreated(now);
		user.setCreater("System");
		user.setUpdated(now);
		user.setUpdater("System");
		if (user.getId() == null) {
			getService().create(user);
		} else {
			getService().update(user);
		}
		return toJson(new JsonResult(user));
	}
}
