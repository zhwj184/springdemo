package org.springweb.service.impl;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springweb.service.HelloService;
import org.springweb.service.RemoteReadService;

//@Component("HelloService3")
public class HelloServiceImpl implements HelloService {

	
	private String greetting;
	
	private Map<Integer,String> typeMap ;
	
//	@Resource
	@Autowired
	@Qualifier("remoteReadServicepostProcessor")
	private RemoteReadService remoteReadServicepostProcessor;
	
	
	//singleton,prototype
	public HelloServiceImpl(){
		System.out.println("call HelloServiceImpl construtor");
	}
	//构造方法注入
	public HelloServiceImpl(String greetting,Map<Integer,String> typeMap){
		this.greetting = greetting;
		this.typeMap = typeMap;
		System.out.println("call HelloServiceImpl constructor" + this.greetting);
	}
	
	@Override
	public void sayHello() {
		System.out.println(remoteReadServicepostProcessor.getCon());
	}

	
	@PostConstruct
	public void init(){
		System.out.println("call init method");
		//本地缓存，socket连接池初始化等初始化工作
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("call destroy method");
		//资源关闭
	}

	public String getGreetting() {
		return greetting;
	}

	//setter注入
	public void setGreetting(String greetting) {
		this.greetting = greetting;
	}

	public Map<Integer,String> getTypeMap() {
		return typeMap;
	}

//	@Autowired
	public void setTypeMap(Map<Integer,String> typeMap) {
		this.typeMap = typeMap;
	}

	public RemoteReadService getRemoteReadService() {
		return remoteReadServicepostProcessor;
	}

//	@Autowired
//	public void setRemoteReadService(RemoteReadService remoteReadService) {
//		this.remoteReadService = remoteReadService;
//	} 

}
