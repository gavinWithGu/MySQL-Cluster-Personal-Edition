package com.gavin.study.mysqlcluster.portal.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gavin.study.mysqlcluster.common.spring.context.BeanFactory;
import com.gavin.study.mysqlcluster.core.zookeeper.config.listerner.IDynamicConfigFileListener;
import com.gavin.study.mysqlcluster.core.zookeeper.wrapper.DynamicConfigFileListenerWrapper;

/**
 */
public class StartupListener extends ContextLoaderListener {
	private Logger logger = Logger.getLogger(StartupListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ServletContext servletContext = event.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		
		BeanFactory.getInstance().setContext(ctx);
		logger.info("Load Spring context into BeanFactory...");
		/**
		 * 注册动态配置文件服务类
		 */
		//1. 监听配置文件内容 变化，动态更新
		IDynamicConfigFileListener dynamicConfigFileListener = DynamicConfigFileListenerWrapper.getInstance().getListener();
		dynamicConfigFileListener.registerListener();

		logger.info("Load data from zookeeper server and register listener...");
	}

}
