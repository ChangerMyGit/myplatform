package com.oecp.myplatform.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oecp.myplatform.common.controller.BaseController;
import com.oecp.myplatform.common.service.BaseService;
import com.oecp.myplatform.common.utils.ConstantUtils;
import com.oecp.myplatform.common.vo.MenuNodeVO;
import com.oecp.myplatform.model.FunctionNode;
import com.oecp.myplatform.service.FunctionService;
@Controller
@RequestMapping("/function")
public class FunctionController extends BaseController<FunctionNode>{

	@Autowired
	private FunctionService functionService;
	
	@Override
	protected BaseService getService() {
		return functionService;
	}

	@RequestMapping(value = "/getMenu", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getTreeMenu() {
		List<FunctionNode> nodes = functionService.findByCondition(" o.parent is null ", null);
		List<MenuNodeVO> result = new ArrayList<MenuNodeVO>();
		for (FunctionNode node : nodes) {
			MenuNodeVO menu = buildMenuNode(node);
			List<FunctionNode> children = node.getChildren();
			buildChildren(children, menu);
			result.add(menu);
		}
		return toJson(result);
	}

	// 构建节点元素
	private MenuNodeVO buildMenuNode(FunctionNode node) {
		MenuNodeVO menu = new MenuNodeVO();
		menu.setId(node.getId());
		menu.setText(node.getText());
		if (StringUtils.isNotBlank(node.getUrl())) {
			Map attributes = new HashMap();
			attributes.put("url", node.getUrl());
			menu.setAttributes(attributes);
		} else {
			menu.setAttributes(null);
		}
		return menu;
	}
	
	// 构建子树
	private void buildChildren(List<FunctionNode> nodes ,MenuNodeVO parent) {
		List<MenuNodeVO> children = new ArrayList<MenuNodeVO>();
		if (nodes != null && nodes.size() != 0) {
			for (FunctionNode node : nodes) {
				MenuNodeVO menu = buildMenuNode(node);
				buildChildren(node.getChildren(),menu);
				children.add(menu);
			}
			parent.setState(ConstantUtils.CLOSED);
			parent.setChildren(children);
		}
	}
}
