package com.kh.lclinic.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect			// AOP 적용
@Component		// 빈 등록
public class LoggingAOP {

	@Pointcut("execution(* com.kh.lclinic..*.*(..))")
	public void cut() {}
	
	@Before("cut()")
	public void before(JoinPoint jp) {
		MethodSignature ms = (MethodSignature)jp.getSignature();
		Object[] args = jp.getArgs();

		// debug : 디버깅할때, 추적하고자 할 때 사용하는 레벨
		// info	 : 흐름 상 남겨놓고자 하는 데이터를 출력할 때 사용 레벨
		// error : 오류 발생 시 사용 레벨
		
		log.info("-------------- before method ------------------");
		log.info("method: {}", ms);
		log.info("args  : {}", args);
	}
	
	@AfterReturning(value="cut()", returning="obj")
	public void afterReturning(JoinPoint jp, Object obj) {
		log.info("-------------- after method ------------------");
		log.info("method: {}", jp.getSignature());
		log.info("object: {}", obj);
	}
	
	@Around("cut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		
		Object result = pjp.proceed();
		
		long end = System.currentTimeMillis();
		
		log.info("------------------------------------");
		log.info("method: {}", pjp.getSignature());
		log.info("time : {}ms", (end-start));
		
		return result;
	}
}
