package net.gplatform.sudoor.server.spring;

/*
 * #%L
 * sudoor-server-lib
 * %%
 * Copyright (C) 2013 - 2014 Shark Xu
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * NOTE:
 * This Class should only be used in those Object not managed by SIC
 */

@Component
public class SpringContextsUtil implements ApplicationContextAware {
	final Logger logger = LoggerFactory.getLogger(SpringContextsUtil.class);

	private static ApplicationContext applicationContext;    //Spring应用上下文环境

	public SpringContextsUtil(){
		logger.debug("Loading SpringContextsUtil");
	}

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 *
	 * @param applicationContext
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.debug("Inject ApplicationContext: {} into SpringContextsUtil", applicationContext);
		SpringContextsUtil.applicationContext = applicationContext;
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取对象
	 *
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	/**
	 * 获取类型为requiredType的对象
	 * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
	 *
	 * @param name         bean注册名
	 * @param requiredType 返回对象类型
	 * @return Object 返回requiredType类型对象
	 * @throws BeansException
	 */
	public static Object getBean(String name, Class requiredType) throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 *
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 *
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 */
	public static Class getType(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}

	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 *
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}
}