package com.example.practice1.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(public * com.example.practice1.rest..*.*(..))")
    private void controllerPointcut() {
        // As a general rule, the @Pointcut annotated method must have an empty method body and must not have any throws clause.
    }

    @Before("controllerPointcut()")
    public void logControllerBefore(JoinPoint jp) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String remoteAddr = request.getRemoteAddr();
        Object[] jsonString = jp.getArgs();


        log.info("--- request information -------");
        log.info("remote address: {}", remoteAddr);
        log.info("remote user: {}", request.getRemoteUser());
        log.info("request URI: {}", requestURI);
        logInfoControllerClass(jp);
        logInfoMethodSignature(jp);
        log.info("method type: {}", method);
        log.info("request parameters: {}", jsonString);
        log.info("--- request information -------");
    }


    @AfterReturning(value = "controllerPointcut()", returning = "val")
    public void logControllerAfter(JoinPoint jp, Object val) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletResponse response = servletRequestAttributes.getResponse();

        assert response != null;
        Collection<String> headerNames = response.getHeaderNames();
        int status = response.getStatus();


        log.info("--- response information -------");
        logInfoControllerClass(jp);
        logInfoMethodSignature(jp);
        log.info("Status: {}", status);
        headerNames.forEach(h -> log.info("Header: {}-{}", h, response.getHeaders(h)));
        log.info("Returning value: {}", val);
        log.info("--- response information -------");

    }

    @AfterThrowing(value = "controllerPointcut()", throwing = "ex")
    public void logControllerAfterThrowingException(JoinPoint jp, Exception ex) {
        log.info("--- response throw exception information -------");
        logInfoControllerClass(jp);
        logInfoMethodSignature(jp);
        log.info("args: {}", jp.getArgs());
        log.info("Ex: {}", ex.toString());
        log.info("--- response throw exception information -------");
    }

    private void logInfoMethodSignature(JoinPoint jp) {
        log.info("method signature: {}", jp.getSignature());
    }

    private void logInfoControllerClass(JoinPoint jp) {
        log.info("controller: {}", jp.getTarget().getClass());
    }
}
