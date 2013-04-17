package org.springweb.context.test;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springweb.dao.Dataobject;
import org.springweb.dao.JdbcTemplateTest;
import org.springweb.dao.MyIbatisTest;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * 
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
 *  
 * @author weijian.zhongwj
 * 
 */
public class SpringContextMVCMain {

	public static void main(String[] args) throws IOException {

	}
}
