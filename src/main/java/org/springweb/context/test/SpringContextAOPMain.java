package org.springweb.context.test;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springweb.aop.HelloServiceImpl;
import org.springweb.aop.HelloServiceWithoutInterface;
import org.springweb.aop.ProxyFactoryProxyAdvisor;
import org.springweb.aop.SpringHelloServiceImpl;
import org.springweb.service.HelloService;

/**
 * spring第二次课
 * 0.web开发环境，eclipse j2ee,jetty 插件，tomcat插件
 * spring框架设计理念（POJO化）
 * 1.AOP概念，应用场景（安全检查，日志记录，监控，事务管理）
 * 2.实现方式（动态代理（所有实现横切关注点的逻辑都得实现InvokationHandler接口），动态字节码增强（生成子类，将横切逻辑加到子类中，不需要实现接口，但是父类不能为final））
 *          spring 默认用动态代理，不能再用字节码（ASM，CGLIB）增强
 * 3. joinpoint(切入点),pointcut（切入点表达式，正则，方法名等），advice（before advice，after advice（after return advice，
 *     after throwing advice, after finally advice,），around advice(filter)），spring AOP 使用proxyfactory完成织入工作。
 * 4. 实现原理：代理模式， 动态代理示例，cglib示例
 * 5. PointCut，JdkRegexpMethodPointcut，NameMatchMethodPointcut
 * 6. advice AfterAdvice BeforeAdvice AspectJAroundAdvice ThrowsAdvice AfterReturningAdvice(批处理方法状态插入数据库）
 * 7. advisor Advisor DefaultPointcutAdvisor order
 * 9. 其他 AspjectJ ajc编译器，编译时织入；JBoss AOP使用自定义的classloader；spring aop使用ProxyFactory  ProxyFactory基于类的代理  AOPProxy AOPProxyFactory
 
 * 10. proxyFactoryBean
 * 11. 自动代理 基于BeanPostProcessor， BeanNameAutoProxyCreator和DefaultAdvisorAutoProxyCreator（针对advisor，自动扫描xml中所有的advisor配置生成代理对象）两个自动代理实现	
 * 12. TargetSource HotSwappableTargetSource（实现数据源替换，热备呼唤） PrototypeTargetSource SingletonTargetSource
 * 
 * 13. aspectj annotation
 * @author weijian.zhongwj
 *
 */

public class SpringContextAOPMain {

	public static void main(String[] args) {
		//编码方式，xml配置方式，properties方式
		//FileSystemXmlApplicationContext ClassPathXmlApplicationContext XmlWebApplicationContext
//		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
//		HelloService helloSerice = (HelloService) context.getBean("helloService");
//		helloSerice.sayHello();
//		
		
		//基于proxyFactory 的基于接口代理实现
		ProxyFactory weaver = new ProxyFactory(new SpringHelloServiceImpl());  
//		weaver.setInterfaces(interfaces);
//		weaver.setExposeProxy(true);
		NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();  
		advisor.setMappedName("sayHello");  
		advisor.setAdvice(new ProxyFactoryProxyAdvisor());  
		weaver.addAdvisor(advisor);  
		HelloService proxyObject =(HelloService)weaver.getProxy();  
		proxyObject.sayHello();
		System.out.println(proxyObject.getClass()); 
		
		//基于proxyFactory的基于类的代理实现
		ProxyFactory weaver1 = new ProxyFactory(new HelloServiceWithoutInterface());  
//		weaver.setInterfaces(interfaces);
		NameMatchMethodPointcutAdvisor advisor1 = new NameMatchMethodPointcutAdvisor();  
		advisor1.setMappedName("sayHello");  
		advisor1.setAdvice(new ProxyFactoryProxyAdvisor());  
		weaver1.addAdvisor(advisor1);  
		HelloServiceWithoutInterface proxyObject1 =(HelloServiceWithoutInterface)weaver1.getProxy();  
		proxyObject1.sayHello();
		System.out.println(proxyObject1.getClass()); 
		
		//默认基于接口的代理，可以通过设置下面两个强制基于类的代理
		weaver.setProxyTargetClass(true);
		SpringHelloServiceImpl proxyObject2 =(SpringHelloServiceImpl)weaver.getProxy();  
		proxyObject2.sayHello();
		System.out.println(proxyObject2.getClass()); 
		
		//proxyFactoryBean FactoryBean的proxy代理实现  xml配置AOP
		ApplicationContext context = new ClassPathXmlApplicationContext("aopbean.xml");
		HelloService helloService = (HelloService) context.getBean("myproxefactorybean");
		helloService.sayHello();
		
		HelloServiceImpl HelloServiceImpl = (HelloServiceImpl) context.getBean("myproxefactorybean");
		HelloServiceImpl.sayHello();
		
		//自動代理實現
		HelloService autoProxyHelloService = (HelloService) context.getBean("autoproxyhelloService");
		autoProxyHelloService.sayHello();
		
	}
}

