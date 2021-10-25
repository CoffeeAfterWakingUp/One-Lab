package com.example.practice1.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(public * com.example.practice1.service.Service.*(..))")
    private void serviceMethodsLogger() {
    }

    @Pointcut("execution(public * com.example.practice1.service.Service.getAuthorsByBookId(Long)) && args(id)")
    private void getAuthorsByBookId(Long id) {
    }


    @Around("serviceMethodsLogger()")
    public Object methodTimeLoggerAroundAdvice(ProceedingJoinPoint call) throws Throwable {
        StopWatch clock = new StopWatch(call.toString());
        try {
            clock.start(call.toShortString());
            return call.proceed();
        } finally {
            clock.stop();
            System.out.println(clock.prettyPrint());
        }
    }

    @After(value = "getAuthorsByBookId(id)", argNames = "id")
    public void afterGetAuthorsByBookId(Long id) {
        log.info("Authors by book id {}", id);
    }

    @AfterReturning(pointcut = "serviceMethodsLogger()", returning = "val")
    public void logAfterReturningValue(JoinPoint jp, Object val) {
        log.info("Method signature: {}", jp.getSignature());
        log.info("Returning: {}", val);
    }

    @AfterThrowing(pointcut = "serviceMethodsLogger()", throwing = "ex")
    public void logServiceExceptions(JoinPoint jp, Exception ex) {
        log.error("Methods signature: {}", jp.getSignature());
        log.error("Exception: {}", ex.toString());
    }


}
