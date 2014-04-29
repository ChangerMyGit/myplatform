package com.oecp.myplatform.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring IOC上下文工具类
 * 
 * @author Changer
 *
 */
@Component
public class SpringUtil implements ApplicationContextAware {

	/**
	 * 自动装配注解会让Spring通过类型匹配为beanFactory注入示例
	 */
	@Autowired
	private static ApplicationContext applicationContext;

	private SpringUtil() {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 根据名称获取bean
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
	
	/**
	 * 根据类型获取bean
	 * @param clazz
	 * @return
	 */
	public static Object getBean(Class clazz) {
		return applicationContext.getBean(clazz);
	}
}
