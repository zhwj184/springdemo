package org.springweb.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springweb.service.HelloService;

/**
 * 静态代理：
 * 代理模式实现：可以在方法调用前后加入自己的业务逻辑，加入更多功能，而不影响对外的调用。
 * 
 * 需要实现跟目标对象同样的接口，可以针对所有接口的实现的代理，但是如果每个service都要这么做，数量会很大，不好管理维护
 * 
 * @author weijian.zhongwj
 *
 */
public class ProxyHelloServiceImpl implements HelloService {

	@Autowired
	private HelloService helloService;

	public ProxyHelloServiceImpl(HelloService helloService){
		this.helloService = helloService;
	}
	
	@Override
	public void sayHello() {
		System.out.println("before method sayhello call");
		helloService.sayHello();
		System.out.println("after method sayhello call");
	}


}
