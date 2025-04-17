package com.ys.customannotations.customAnnotation.PreventDuplicate;
import com.ys.customannotations.exception.DuplicateRequestException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Aspect
@Component
@Slf4j
public class PreventDuplicateAspect {


    private final ConcurrentHashMap<String, Long> requestCache = new ConcurrentHashMap<>();

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();


    @Around("@annotation(preventDuplicate)")
    public Object preventDuplicateSubmission(ProceedingJoinPoint joinPoint, PreventDuplicate preventDuplicate) throws Throwable {
        String key = generateKey(joinPoint);

        log.warn("Generated key: {}", key);

        if (requestCache.containsKey(key)) {
            log.warn("Duplicate submission detected for key: {}", key);
            throw new DuplicateRequestException("Duplicate request detected. Please wait before trying again.");
        }

        requestCache.put(key, System.currentTimeMillis());
        scheduler.schedule(() -> {
            requestCache.remove(key);
            log.debug("Key removed after expiration: {}", key);
        }, preventDuplicate.expiration(), TimeUnit.MILLISECONDS);

        return joinPoint.proceed();
    }

    private String generateKey(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.toShortString();
        int paramsHash = Arrays.hashCode(joinPoint.getArgs());

        return methodName + "_" + paramsHash;
    }
}