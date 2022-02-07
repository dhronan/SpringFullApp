package com.sample.example.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;

@Aspect
@Slf4j
@Configuration

public class LoggerAspect {

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void annotatedWithRequestMapping(){}
	
	
	@Before("annotatedWithRequestMapping()")
	void advice(JoinPoint jp) {
		//log.info(jp.getTarget().getClass().getName()+" Controller's method "+jp.getSignature().getName()+" Arguments : " +jp.getArgs()[0].toString()+" Started.");
	}
	
	
}
