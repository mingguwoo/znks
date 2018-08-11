package com.sh.znks.common.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by wuminggu on 2018/6/15.
 */
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@SuppressWarnings("unchecked")
	public static <T> T getBeanById(String id) {
		return (T) applicationContext.getBean(id);
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

}
