package org.springweb.aop.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class UserAdvice {

	private static int n = 1;

	@Before("execution (* org.springweb.aop.aspectj.UserService.print(..))")
	public void test() {
		System.out.println("advice test method n = " + n);
		n++;
	}
}
