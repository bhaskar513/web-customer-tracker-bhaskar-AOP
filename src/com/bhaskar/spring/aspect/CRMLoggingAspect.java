/**
 * 
 */
package com.bhaskar.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

/**
 * @author bhaskar
 *
 * 
 */

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// setup pointcut
	@Pointcut("execution(* com.bhaskar.spring.controller.*.*(..))")
	private void forControllerPackage() {
	}

	// do for same for service and dao
	@Pointcut("execution(* com.bhaskar.spring.service.*.*(..))")
	private void forServicePackage() {
	}

	//do for same for dao
	@Pointcut("execution(* com.bhaskar.spring.dao.*.*(..))")
	private void forDaoPackage() {
	}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage() ")
	 private void forAppFlow() {
	
	}
	
	//add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint)
	{
		
		// display method we are calling
		String theMethod=theJoinPoint.getSignature().toShortString();
		myLogger.info("============ in @Before : calling method : " + theMethod);
		
		// display the arguments
		Object[] args=theJoinPoint.getArgs();
		
		//display args
		
		for(Object tempArg: args)
		{
			myLogger.info("=========== arguments" +tempArg);
		}
}
	
	
	// add @AfterReturning advice
	
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult"
			)
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		
		// display method returned
		String theMethod=theJoinPoint.getSignature().toShortString();
		myLogger.info("============ in @AfterReturning : calling method : " + theMethod);
	
	// display data returned
		myLogger.info("================== result" + theResult);
	
	}
	
}
