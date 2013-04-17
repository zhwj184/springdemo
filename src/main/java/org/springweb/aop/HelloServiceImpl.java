package org.springweb.aop;

import org.springweb.service.HelloService;

public class HelloServiceImpl implements HelloService{

	@Override
	public void sayHello() {
		System.out.println("helloserviceimpl say hello");
	}

}
