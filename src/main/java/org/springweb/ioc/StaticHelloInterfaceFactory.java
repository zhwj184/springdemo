package org.springweb.ioc;

import org.springweb.service.HelloService;
import org.springweb.service.impl.HelloServiceImpl;

public class StaticHelloInterfaceFactory {

	public static HelloService getInstance(){
		return new HelloServiceImpl("", null);
	}
}
