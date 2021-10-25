package com.example.practice1.aspect;

import com.example.practice1.exception.BookIsNullException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CheckBookIdAspect {


    @Pointcut("execution(public * com.example.practice1.service.Service.getAuthorsByBookId(Long))")
    private void checkBookId() {
    }


    @Before("checkBookId()")
    public void beforeGetAuthorsByBookId(JoinPoint jp) {
        Long bookId = (Long) jp.getArgs()[0];
        if (bookId == null) {
            throw new BookIsNullException("Id is null");
        }
    }


}
