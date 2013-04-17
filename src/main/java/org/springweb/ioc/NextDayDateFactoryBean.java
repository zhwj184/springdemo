package org.springweb.ioc;


import java.util.Date;

import org.springframework.beans.factory.FactoryBean;

public class NextDayDateFactoryBean implements FactoryBean{

	@Override
	public Object getObject() throws Exception {
		return new Date(); 
	}

	@Override
	public Class getObjectType() {
		 return Date.class; 
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
