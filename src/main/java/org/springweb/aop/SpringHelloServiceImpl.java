package org.springweb.aop;

import org.springweb.service.HelloService;

/**
 * 
 * @author weijian.zhongwj
 *
 */
public class SpringHelloServiceImpl implements HelloService {

	@Override
	public void sayHello() {
		System.out.println("SpringHelloServiceImpl sayHello");
	}


}
