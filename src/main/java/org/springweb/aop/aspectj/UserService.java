package org.springweb.aop.aspectj;

import org.springframework.stereotype.Service;

@Service 
public class UserService {

	public void print(){
		System.out.println("call userservice print method");
	}
}
