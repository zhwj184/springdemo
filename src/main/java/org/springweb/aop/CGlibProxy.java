package org.springweb.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * CGlib代理， 不需要目标对象实现某个接口，通过子类方式在子类中实现织入逻辑，再调用父类方法
 * @author weijian.zhongwj
 *
 */
public class CGlibProxy implements MethodInterceptor {
	
	private Object target;
	
	public CGlibProxy(){
	}
	
	public CGlibProxy(Object obj){
		this.target = obj;
	}

    /** 
     * 创建代理对象 
     *  
     * @param target 
     * @return 
     */  
    public Object getInstance() {  
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(this.target.getClass());  
        enhancer.setCallback(this);
        // 创建代理对象  
        return enhancer.create();  
    }
 

    @Override  
    // 回调方法  
    public Object intercept(Object obj, Method method, Object[] args,  
            MethodProxy proxy) throws Throwable {  
        System.out.println("事物开始CGLIB");  
        proxy.invokeSuper(obj, args);  
        System.out.println("事物结束CGLIB");  
        return null;  
    }  
    
	public static void main(String[] args) {
		HelloServiceWithoutInterface helloService = new HelloServiceWithoutInterface();
		CGlibProxy cglibProxy = new CGlibProxy(helloService);
		HelloServiceWithoutInterface helloServiceProxy = (HelloServiceWithoutInterface) cglibProxy.getInstance();
		helloServiceProxy.sayHello();
	}
}
