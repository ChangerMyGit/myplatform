package com.oecp.myplatform.controller;

import java.util.ArrayList;
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

@Controller
@RequestMapping("/testAction")
public class TestController extends BaseController<User> {

	@Autowired
	private UserService userService;

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
	public String textTx() {
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

	@RequestMapping(value = "/deleteWhere", method = RequestMethod.GET)
	@ResponseBody
	public String testDeleteWhere() {
		List params = new ArrayList();
		params.add("创建人");
		params.add("更新人");
		userService.deleteByCondition(" o.creater = ? and o.updater = ? ",params);
		return "DeleteWhere Success!";
	}

	@RequestMapping(value = "/findWhere", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String testFindByWhere() {
		List params = new ArrayList();
		params.add("%创建人%");
		params.add("%更新人%");
		List result = userService.findByCondition(" o.creater like ? and o.updater like ? ", params, 0, 100);
		return this.toJson(result);
	}

	@Override
	protected BaseService getService() {
		return userService;
	}
}
