package com.ell.cms.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // 定义一个横切点，表示对哪些类的哪些方法进行拦截
    @Pointcut("execution(* com.ell.cms.service..*(..))")
    public void serviceLayer() {
    }

    // 在方法执行前进行日志记录
    @Before("serviceLayer()")
    public void logBefore() {
        logger.info("Before method execution. Logging aspect triggered.");
    }

    // 在方法执行后进行日志记录
    @After("serviceLayer()")
    public void logAfter() {
        logger.info("After method execution. Logging aspect triggered.");
    }
}
