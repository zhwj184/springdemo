package org.springweb.context.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springweb.aop.aspectj.NextService;
import org.springweb.aop.aspectj.UserService;

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
 * 
 * 13. aspectj annotation
 * @author weijian.zhongwj
 *
 */

public class AspectjSpringContextAOPMain {

	public static void main(String[] args) {

		
		//proxyFactoryBean FactoryBean的proxy代理实现  xml配置AOP
		ApplicationContext context = new ClassPathXmlApplicationContext("aopbeanaspectJ.xml");
		UserService service =(UserService) context.getBean("userService");  
        service.print();  
		
        NextService NextService =(NextService) context.getBean("NextService");  
        NextService.print();  
	}
}

