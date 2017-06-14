package com.gavin.study.mysqlcluster.common.spring.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import com.gavin.study.mysqlcluster.common.thread.DBContextHolder;

@Component
public class BeforeServiceAdvice implements MethodInterceptor, ThrowsAdvice {

	final String CUD_METHOD_NAME="create,update,delete,insert";
	
	public void afterThrowing(Method method, Object[] args, Object target,
			Exception ex) throws Throwable {
		// 逻辑操作，如记录日志
		throw ex;
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		String method = methodInvocation.getMethod().getName().toString();
		if(CUD_METHOD_NAME.contains(method)){
			DBContextHolder.setDBType("1");
		}else{
			DBContextHolder.setDBType("0");
		}
		
		Object obj = methodInvocation.proceed();
				
		return obj;
	}
}
