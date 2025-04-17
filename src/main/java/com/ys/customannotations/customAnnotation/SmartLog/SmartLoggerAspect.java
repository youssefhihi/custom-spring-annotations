package com.ys.customannotations.customAnnotation.SmartLog;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
@Component
@Slf4j
public class SmartLoggerAspect {

    @Around("@annotation(smartLog)")
    public Object logDetailedInfo(ProceedingJoinPoint joinPoint, SmartLog smartLog) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String fullMethod = className + "." + methodName;

        Object[] args = joinPoint.getArgs();
        String[] paramNames = methodSignature.getParameterNames();

        log.info(" Entering method: {}", fullMethod);
        log.info(" Parameters:");
        if (paramNames != null && paramNames.length > 0) {
            for (int i = 0; i < paramNames.length; i++) {
                log.info("   → {} = {}", paramNames[i], args[i]);
            }
        } else {
            log.info("   → No parameters.");
        }

        long start = System.currentTimeMillis();
        Object result;

        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            log.error(" Exception in {}: {}", fullMethod, ex.getMessage(), ex);
            throw ex;
        }

        long duration = System.currentTimeMillis() - start;

        log.info(" Method {} executed in {}ms", fullMethod, duration);
        log.info(" Returned: {}", result);
        if (!smartLog.value().isBlank()) {
            log.info(" Note: {}", smartLog.value());
        }

        log.info(" Exiting method: {}", fullMethod);

        return result;
    }
}
