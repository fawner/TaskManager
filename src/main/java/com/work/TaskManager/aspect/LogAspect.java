package com.work.TaskManager.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("@annotation(com.work.TaskManager.aspect.annotation.Logging)")
    public void logExecution(JoinPoint joinPoint) {
        logger.info("Был вызван метод: " + joinPoint.getSignature().getName());

    }

    @AfterThrowing(
            pointcut = "@annotation(com.work.TaskManager.aspect.annotation.ExceptionHandling)",
            throwing = "exception"
    )
    public void handleException(JoinPoint joinPoint, Exception e) {
        logger.error("Метод {} завершил работу с исключением {}", joinPoint.getSignature().getName(), e.getClass());
    }

    @Around("@annotation(com.work.TaskManager.aspect.annotation.Tracking)")
    public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Был вызван метод: " + joinPoint.getSignature().getName());
        Long start = System.currentTimeMillis();
        Object proceeded = joinPoint.proceed();
        Long end = System.currentTimeMillis();
        logger.info("Метод: " + joinPoint.getSignature().getName() + "закончил свою работу");
        logger.info("Время выполнения метода: " + (end - start));
        return proceeded;
    }

    @AfterReturning(
            pointcut = "@annotation(com.work.TaskManager.aspect.annotation.LogSuccess)",
            returning = "result"
    )
    public void loggingSuccess(JoinPoint joinPoint, Object result) {
        logger.info("метод {} завершил работу с результатом {}", joinPoint.getSignature().getName(), result.toString());

    }
}
