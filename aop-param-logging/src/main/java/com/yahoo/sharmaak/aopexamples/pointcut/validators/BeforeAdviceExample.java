package com.yahoo.sharmaak.aopexamples.pointcut.validators;

import java.lang.reflect.Method;
import java.util.Arrays;
import com.yahoo.sm.ads.common.log.Logger;


import org.springframework.aop.MethodBeforeAdvice;

public class BeforeAdviceExample implements MethodBeforeAdvice {

	private static final Logger logger = Logger.getLogger(BeforeAdviceExample.class);

	public BeforeAdviceExample() {
	}

	public void before(Method method, Object[] args, Object target) throws Throwable {
		
		logger.info("About to execute : " + target.getClass() + "." + method.getName()+"(...)");
		logger.info("Args : " + Arrays.deepToString(args));
	}
}
