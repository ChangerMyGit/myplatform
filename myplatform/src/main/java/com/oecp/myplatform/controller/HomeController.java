package com.oecp.myplatform.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oecp.myplatform.common.controller.BaseController;
import com.oecp.myplatform.common.controller.JsonResult;
import com.oecp.myplatform.common.service.BaseService;
import com.oecp.myplatform.common.utils.SpringUtil;
import com.oecp.myplatform.model.User;
import com.oecp.myplatform.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Object obj = SpringUtil.getBean("restConstroller");
		Object obj2 = SpringUtil.getBean("userDao");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String login(@ModelAttribute User user) {
		List params = new ArrayList();
		params.add(user.getUserName());
        params.add(DigestUtils.md5Hex(user.getPassword()));
        boolean exist =  userService.isExitedByCondition(" o.userName = ? and o.password = ? ", params);
        JsonResult result ;
        if(exist){
        	result = new JsonResult(null);
        	// getSession().setAttribute("userInfo", userService.find(entityId));
        }
        else result = new JsonResult(exist, "用户名密码错误！");
        return toJson(result);
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	@Override
	protected BaseService getService() {
		return userService;
	}
	
}
