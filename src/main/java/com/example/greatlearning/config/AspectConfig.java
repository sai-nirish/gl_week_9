package com.example.greatlearning.config;

import java.util.Date;

import com.example.greatlearning.entity.AuditLog;
import com.example.greatlearning.repository.AuditRepository;
import com.example.greatlearning.service.ItemService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class AspectConfig {

	@Autowired
	AuditRepository auditRepository;

	@Autowired
	ItemService itemService;

	@AfterReturning(
			value = "execution(public * com.example.greatlearning.serviceImpl.ItemsServiceImpl.getAggregatePrice(..) )",
			returning="totalBill")
	public void logBillGeneration(JoinPoint joinPoint, Integer totalBill) {
		String bill = String.valueOf(totalBill);
		auditRepository.saveAndFlush(AuditLog.builder().createDate(new Date())
				.description("Bill generated for users " + joinPoint.getArgs()[0]).billValue(totalBill).build());
	}

	@AfterThrowing(value = "execution(public * com.example.greatlearning.serviceImpl.ItemsServiceImpl.getAggregatePrice(..) )", throwing = "error")
	public void logErrorOnBillGeneration(JoinPoint joinPoint, Exception error) {
		auditRepository.saveAndFlush(AuditLog.builder().createDate(new Date())
				.description("Details of bill generated " + joinPoint.getArgs()[0]).billValue(0).error(error.toString()).build());
	}


}
