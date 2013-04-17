package org.springweb.service.impl;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springweb.bean.User;
import org.springweb.service.RemoteReadService;

public class RemoteReadServiceImpl implements RemoteReadService, InitializingBean, DisposableBean{
	
	private String url;
	private String username;
	private String password;
	
	public RemoteReadServiceImpl(){
		System.out.println("call RemoteReadServiceImpl constuctor");
	}
	
	@Override
	public String getCon() {
		return url + " " + username + " " + password;
	}

	@Override
	public String saveLoginInfo(String id, User user) {
		return "success";
	}

	@Override
	public void destroy() throws Exception {
		System.out.println(" RemoteReadServiceImpl destroy method will call");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(" RemoteReadServiceImpl afterPropertiesSet call init");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
