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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
/*	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String list(@RequestParam int page,@RequestParam int rows){
		Map paramMap = getRequest().getParameterMap();
		System.out.println(paramMap.get("requertUrl"));
		System.out.println(paramMap.get("params"));
		StringBuffer whereSql = new StringBuffer();
		List paramsList = new ArrayList();
		String requertUrl = getRequest().getParameter("requertUrl");
		String params = getRequest().getParameter("params");
		if(StringUtils.isNotBlank(requertUrl)){
			whereSql.append(" and o.requertUrl like ? ");
			paramsList.add("%" + requertUrl + "%");
		}
		if(StringUtils.isNotBlank(params)){
			whereSql.append(" and o.params like ? ");
			paramsList.add("%" + params + "%");
		}
		List result = getService().findByCondition(whereSql.toString(), paramsList, (page-1)*rows, rows);
		Long total = getService().getCountByCondition(whereSql.toString(), paramsList);
	    Map map = new HashMap();
	    map.put("total", total);
	    map.put("rows", result);
		return toJson(map);
	}*/
}
