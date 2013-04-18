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
    * 10 messagesource，propertyplaceholder
执行SpringContextIOCMain main函数
   

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

2.AOP 主要内容和示例
   
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
    
执行SpringContextAOPMain main函数
    
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

3. 基于AspectJ的AOP示例，执行AspectjSpringContextAOPMain main函数

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


4. spring DAO ，包括JDBCTemplate,集成ibatis和hibernate示例

     * 1.DAO Data acess object
    *  2. DataAccessException 数据库访问如何封装底层各个数据库的异常信息
    * 统一数据访问异常体系 
    * 3. jdbc API JDBCTemplate（封装所有jdbc访问代码，统一格式与规范 SimpleJdbcOperations
    *   SimpleJdbcTemplate，NameParameterJdbcTemplate
    * 4. DataSource DriverManagerDataSource SimpleDriverDataSource c3p0 dbcp
    *  4. 设计模式：模板方法 
    *  5. 基于操作对象的访问方式 SqlQuery,SqlUpdate,SqlCall,SqlFunction
    * 
    *   RDBMSOperation
    * 
    * 
    * 
    * 6.ORM： ibatis，hibernate  hibernateTemplate demo
    * 7. JDO java data object 对象持久化规范  JPA java持久化API
    * 8 扩展 FTPClientTemplate HttpClientTemplate
    * 
    
    
执行 SpringContextDAOMain main函数
   
    public class SpringContextDAOMain {

      
         public static void main(String[] args) throws IOException {
      		ApplicationContext context = new ClassPathXmlApplicationContext("jdbcbean.xml");
      		JdbcTemplateTest jdbcTemplateTest = (JdbcTemplateTest) context.getBean("jdbctest");
      //		jdbcTemplateTest.query();
      //		jdbcTemplateTest.insert();
      //		jdbcTemplateTest.delete();
      //		jdbcTemplateTest.insertBatch();
      //		jdbcTemplateTest.update();
      //		jdbcTemplateTest.updataBatch();
      //		jdbcTemplateTest.create_or_dropTable();
      //		jdbcTemplateTest.callpreparecall();
      		
      		//ibatis
      		//spring xml声明配置方式
      		MyIbatisTest myIbatisTest = (MyIbatisTest) context.getBean("myIbatisTest");
      		myIbatisTest.query();
      		//ibatis 编码方式
      		SqlMapClient sqlmap = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader("ibatis/mysql-sqlmap-config.xml"));
      		SqlMapClientTemplate sqlMapClientTemplate = new SqlMapClientTemplate((DataSource) context.getBean("dbcpDataSource"),sqlmap);
      		Map<String,Object> param = new HashMap<String,Object>();
      		param.put("a", 2);
      		List<Dataobject> list = sqlMapClientTemplate.queryForList("test.query", param);
      		System.out.println(list);
      		
      		
      		//hibernate
      		 //从Spring容器中请求SessionFactory
      		ApplicationContext hibernate = new ClassPathXmlApplicationContext("jdbchibernatebean.xml");
              SessionFactory factory =(SessionFactory)hibernate.getBean("sessionFactory");  
              Session session = factory.openSession();  
              //query
              Dataobject data = (Dataobject)session.get(Dataobject.class, 2);  
              System.out.println(data);
              //insert
              Dataobject data1 = new Dataobject();
              data1.setA(14);
              data1.setB("12bbb");
              Serializable s = session.save(data1);
              session.flush();
              System.out.println(s);
              Dataobject data3 = (Dataobject)session.get(Dataobject.class, 12);  
              System.out.println(data3);
              //update
              data1.setA(12);
              data1.setB("123bbb");
              session.update(data1);
              data3 = (Dataobject)session.get(Dataobject.class, 12);  
              System.out.println(data3);
              //session事务
              Transaction transaction = session.beginTransaction();
              session.save(data1);
              session.save(data3);     
              transaction.commit();
              
              session.close();  
      	}
      }
      

5. spring MVC，用eclipse jetty插件直接执行该工程即可。具体查看相关配置web.xml和mv-dispatch-servlet.xml配置，有jsp和velocity支持


    * 1. servlet,jsp, mvc
    * 2. spring mvc; DispatchServlet
    *    HandlerMapping,Controller,ViewResolver,view
    * 3. WebApplicationContextUtils
    * 
    * 4. HandlerMapping  order SimpleUrlHandlerMapping( href="requestpath" -> bean name="/requestpath")  
    *    ControllerClassNameHandlerMapping  DefaultAnnotationHandlerMapping
    *    
    * 5. Controller (AbstractController,MultiActionController),BaseCommandController,AbstractWizardFormController,ParameterizableViewController,
    *    UrlFilenameViewController,ServletForwardingController,ServletWrappingController
    * 
    * 6. ViewResolver InternalResourceViewResolver(默认，jsp），FreeMarkerViewResolver，VelocityViewResolver，JasperReportsViewResolver
    *    XsltViewResolver  MultiPartResolver, order
    *    jsp,velocity demo
    * 
    * 7. view InternalResourceView JstlView VelocityView ...
    * 
    * 8 MultiPartResolver（demo）, HandlerInteceptor, HandlerAdaptor,HandlerExceptionResolver,LocalResolver,ThemeResolver
    * 9 HandlerAdaptor  HttpRequestHandlerAdapter SimpleControllerHandlerAdapter AnnotationMethodHandlerAdapter
    * 10 HandlerInteceptor WebContentInterceptor UserRoleAuthorizationInterceptor  HandlerInterceptorAdapter
    * 11 HandlerExceptionResolver SimpleMappingExceptionResolver
    * 12 LocalResolver FixedLocaleResolver SessionLocaleResolver AcceptHeaderLocaleResolver CookieLocaleResolver  LocaleChangeInterceptor
    * 13 ThemeResolver FixedThemeResolver resolveThemeName CookieThemeResolver  ThemeChangeInterceptor
    * 
    * 14 基于注解的mvc
    
6. spring与j2ee的相关集成，示例包括spring mail和quartz，其他方面请查看相关博客介绍


    * 1. jndi  JndiTemplate JndiObjectFactoryBean  http://liuzidong.iteye.com/blog/962841
    * 2. jms JmsTemplate
    * 3. Email
    * 4. 任务调度和线程池  Quartz，ScheduledTimeTask
    * 5. 远程调用方案  RMI,HTTP,HESSION,Burlap,WebService(JAX-RPC,JAX-WS），jms
    *    http://zhwj184.iteye.com/admin/blogs/1683252 rmi
    *    http://zhwj184.iteye.com/admin/blogs/1683626 webservice
    *    http://www.ibm.com/developerworks/cn/java/wa-spring4/ jms
    *    http://chenjumin.iteye.com/blog/234897  hession
    *    http://victor-jan.iteye.com/blog/823550  httpinvoker
    *    http://topmanopensource.iteye.com/blog/350105 burlap
