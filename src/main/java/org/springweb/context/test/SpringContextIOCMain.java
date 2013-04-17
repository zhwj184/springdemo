package org.springweb.context.test;

import java.util.Date;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springweb.service.HelloService;
import org.springweb.service.RemoteReadService;

/**
 * spring第一次课
 * 0.web开发环境，eclipse j2ee,jetty 插件，tomcat插件
 * spring框架设计理念（POJO化）
 * 1.IOC 概念，作用，优点 Donnot call me,we will call you.  beanfactory，applicationcontext（事件发布，国际化支持）
 * 2.注入方法 setter，construtor（参数太多，可变参数列表），接口注入  
 * 3.注入类型 bean，基本类型，map，list等
 * 4.注入方式 byname，bytype，default
 * 5.annotation（autowired,resource,@Qualifier）
 * 6.bean的生命周期 init-method,destroy-method,initiableBean, DisposableBean,lasyinit，singleton，prototype @PostConstruct @PreDestroy
 * 7.postProcessBean
 * 8.IOC 应用场景，datasouce配置，线程池配置，
 * 9.工厂方法与 FactoryBean
 * 
 * @author weijian.zhongwj
 *
 */

public class SpringContextIOCMain {

	public static void main(String[] args) {
		//编码方式，xml配置方式，properties方式
		//FileSystemXmlApplicationContext ClassPathXmlApplicationContext XmlWebApplicationContext
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		HelloService helloSerice = (HelloService) context.getBean("helloService");
		helloSerice.sayHello();
		
		HelloService helloSerice1 = (HelloService) context.getBean("helloService");
		helloSerice1.sayHello();
		
		
		//remoteReadServicepostProcessor
//		RemoteReadService remotePostProcess = (RemoteReadService) context.getBean("remoteReadServicepostProcessor");
//		System.out.println(remotePostProcess.getCon());
//		
		//messageSource
//		MessageSource messageSource = (MessageSource) context.getBean("messageSource");
//		System.out.println(messageSource.getMessage("loginfail", new Object[]{"a","b"}, Locale.ENGLISH));
//		System.out.println(messageSource.getMessage("loginfail", new Object[]{new Date().toString()}, Locale.getDefault()));
	}
}
