package org.springweb.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ProxyFactoryProxyAdvisor implements MethodInterceptor {

	
	public ProxyFactoryProxyAdvisor(){
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("ProxyFactoryProxy before method call");
		Object obj = invocation.proceed();
		System.out.println("ProxyFactoryProxy after method call");
		return obj;
	}
 
    
}