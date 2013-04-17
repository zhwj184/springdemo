package org.springweb.service;

import org.springweb.bean.User;

public interface RemoteReadService {
	
	public String getCon();
	
	public String saveLoginInfo(String id, User user);
}
