package com.oecp.myplatform.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oecp.myplatform.common.controller.BaseController;
import com.oecp.myplatform.common.service.BaseService;
import com.oecp.myplatform.model.FunctionNode;
import com.oecp.myplatform.model.User;
import com.oecp.myplatform.service.FunctionService;
import com.oecp.myplatform.service.UserService;

@Controller
@RequestMapping("/testAction")
public class TestController extends BaseController<User> {

	@Autowired
	private UserService userService;
	@Autowired
	private FunctionService functionService;

	@RequestMapping(value = "/initUser", method = RequestMethod.GET)
	@ResponseBody
	public String updateTest() {
		Date now = new Date();
	    User user = new User();
	    user.setUserName("admin");
	    user.setPassword(DigestUtils.md5Hex("admin"));
	    user.setCreated(now);
	    user.setCreater("admin");
	    user.setUpdated(now);
	    user.setUpdater("admin");
	    userService.create(user);
		return "InitUser Success!";
	}

	@RequestMapping(value = "/testTx", method = RequestMethod.GET)
	@ResponseBody
	public String textTx() {
		User user1 = new User();
		user1.setUserName("testTX11");
		user1.setCreater("testTX11");
		user1.setUpdater("testTX11");

		User user2 = new User();
		user2.setUserName("testTX22");
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
		userService.deleteByCondition(" and o.creater = ? and o.updater = ? ",params);
		return "DeleteWhere Success!";
	}

	@RequestMapping(value = "/findWhere", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String testFindByWhere() {
		List params = new ArrayList();
		params.add("%创建人%");
		params.add("%更新人%");
		List result = userService.findByCondition(" and o.creater like ? and o.updater like ? ", params, 0, 100);
		return this.toJson(result);
	}
	
	@RequestMapping(value = "/initTree", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String initTree(){
		Date now = new Date();
/*		FunctionNode node = new FunctionNode();
		node.setText("组织机构");
		node.setCreated(now);
		node.setCreater("System");
		node.setUpdated(now);
		node.setUpdater("System");
		
		List<FunctionNode> children = new ArrayList<FunctionNode>();
		
		FunctionNode node1 = new FunctionNode();
		node1.setText("部门管理");
		node1.setCreated(now);
		node1.setCreater("System");
		node1.setUpdated(now);
		node1.setUpdater("System");
		node1.setParent(node);
		
		FunctionNode node2 = new FunctionNode();
		node2.setText("人员管理");
		node2.setCreated(now);
		node2.setCreater("System");
		node2.setUpdated(now);
		node2.setUpdater("System");
		node2.setParent(node);
		children.add(node1);
		children.add(node2);
		node.setChildren(children);
		functionService.create(node);*/
		FunctionNode node = functionService.find("40288c0845d049810145d04a23cc0000");
		FunctionNode node2 = new FunctionNode();
		List<FunctionNode> children = new ArrayList<FunctionNode>();
		node2.setText("角色管理");
		node2.setCreated(now);
		node2.setCreater("System");
		node2.setUpdated(now);
		node2.setUpdater("System");
		node2.setParent(node);
		children.add(node2);
		node.setChildren(children);
		
		FunctionNode newChileRen = new FunctionNode();
		newChileRen.setText("角色管理1");
		newChileRen.setCreated(now);
		newChileRen.setCreater("System");
		newChileRen.setUpdated(now);
		newChileRen.setUpdater("System");
		newChileRen.setParent(node2);
		
		List ll = new ArrayList();
		ll.add(newChileRen);
		node2.setChildren(ll);
		
		functionService.create(node);
		return "Init Node Success!";
	}
	
	@Override
	protected BaseService getService() {
		return userService;
	}
}
