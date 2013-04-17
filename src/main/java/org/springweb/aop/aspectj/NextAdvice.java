package org.springweb.aop.aspectj;

import org.aspectj.lang.JoinPoint;

public class NextAdvice {
	
	public void doBefore(JoinPoint point){
		System.out.println("NextAdvice do before");
	}

}
