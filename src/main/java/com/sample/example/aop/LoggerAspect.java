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

	@Pointcut("execution(* com.sample.example.controller.*rod*..*(..))")
	public void dataLayerExecution(){}
	
	@Before("dataLayerExecution()")
	void advice(JoinPoint jp) {
		log.info("Method "+jp+ " "+jp.getArgs()[0].toString()+" Started.");
	}
}
