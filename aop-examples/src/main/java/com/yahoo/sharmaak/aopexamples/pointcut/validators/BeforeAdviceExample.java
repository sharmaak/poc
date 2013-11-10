package com.yahoo.sharmaak.aopexamples.pointcut.validators;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class BeforeAdviceExample implements MethodBeforeAdvice {


	public BeforeAdviceExample() {
	}

	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println("[AOP]: Inside: " + getClass() + ".before()");
		System.out.println("[AOP]: About to execute : " + target.getClass() + "." + method.getName());
		System.out.println("[AOP]: Args : " + args);
	}

}
