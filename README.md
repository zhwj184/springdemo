springdemo
==========

spring3的IOC，AOP，JDBC，orm等各个模块的使用示例集锦，可以作为入门的spring学习示例教程

在org.springweb.context.test包下
1.IOC
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
