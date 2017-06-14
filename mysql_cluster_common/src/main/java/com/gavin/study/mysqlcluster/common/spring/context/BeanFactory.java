package com.gavin.study.mysqlcluster.common.spring.context;

import org.springframework.context.ApplicationContext;

/**
 * Spring容器实体获取工厂类
 *
 * @author jacky
 */
public class BeanFactory
{
	private ApplicationContext	context		= null;

	private static BeanFactory	singleton	= null;

	public static BeanFactory getInstance()
	{
		if (singleton == null)
		{
			singleton = new BeanFactory();
		}
		return singleton;
	}

	/**
	 * 通过名称获取Spring容器中的实体
	 * @param       
	 * @return     
	 * @throws
	 */
	public Object getBeanByName(String beanName)
	{
		if (context == null)
		{
			return null;
		}
		return context.getBean(beanName);
	}

	public void setContext(ApplicationContext context)
	{
		this.context = context;
	}
}
