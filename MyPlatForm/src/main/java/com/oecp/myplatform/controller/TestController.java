package com.oecp.myplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.oecp.myplatform.common.web.BaseController;
import com.oecp.myplatform.model.User;
import com.oecp.myplatform.service.UserService;

@Controller
@RequestMapping("/testDao")
public class TestController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/save", method = RequestMethod.GET) 
	@ResponseBody
	public String saveTest() {
    	User user1 = new User();
    	user1.setName("testTX");
    	user1.setCreater("testTX");
    	user1.setUpdater("testTX");
    	userService.create(user1);
		return JSON.toJSONString(user1);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET) 
	@ResponseBody
	public String deleteTest() {
    	userService.delete("402886a44597ed98014597edc6bc0000");
		return "Delete Success!";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET) 
	@ResponseBody
	public String updateTest() {
		User user = userService.find("402886a44597d99c014597da215c0000");
		user.setName("Changer Hello");
    	userService.update(user);
		return "Update Success!";
	}
	
	@RequestMapping(value = "/testTx", method = RequestMethod.GET) 
	@ResponseBody
	public String textTx(){
    	User user1 = new User();
    	user1.setName("testTX11");
    	user1.setCreater("testTX11");
    	user1.setUpdater("testTX11");
    	
    	User user2 = new User();
    	user2.setName("testTX22");
    	user2.setCreater("testTX22");
    	user2.setUpdater("testTX22");
    	userService.testTx(user1, user2);
    	return "Tx Success!";
	}
}
