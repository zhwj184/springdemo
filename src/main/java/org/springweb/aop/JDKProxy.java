package org.springweb.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springweb.service.HelloService;

/**
 * jdk动态代理，只要织入逻辑一样，都可以使用同一个代理对象，接口中的方法被调用时，会调用这里的invoke方法
 * 必须实现接口，
 * @author weijian.zhongwj
 *
 */
public class JDKProxy implements InvocationHandler{

	private Object target;
	
	public JDKProxy(Object obj){
		this.target = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("jdk动态代理，方法执行前");
		Object obj = null;
		try {
			obj =  method.invoke(target, args);
		} catch (Exception e) {
			System.out.println("jdk动态代理，抛出异常方法处理");
			throw e;
		}
		System.out.println("jdk动态代理，方法执行后");
		return obj;
	}
	
	public static void main(String[] args) {
		 HelloService helloService = new HelloServiceImpl();
		 JDKProxy helloServiceProxy = new JDKProxy(helloService);
		 HelloService helloProxyService = (HelloService) Proxy.newProxyInstance(helloService.getClass().getClassLoader(),  helloService.getClass().getInterfaces(), helloServiceProxy);   //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
		 helloProxyService.sayHello();
	}

}
