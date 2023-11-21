package ru.wyrd.limiter;

import com.google.common.cache.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Aspect
public class Limiter {
    private final Cache<String, Integer> requestCache;
    private final LimiterProperties properties;

    public Limiter(Cache<String, Integer> requestCache, LimiterProperties properties) {
        this.requestCache = requestCache;
        this.properties = properties;
    }

    @Around("@annotation(ru.wyrd.limiter.Limit)")
    public Object limitedMethod(ProceedingJoinPoint pjp) throws Throwable {
        String sessionId = getSession();
        Integer requestCount = requestCache.getIfPresent(sessionId);
        if (requestCount == null) {
            requestCache.put(sessionId, 1);
            return pjp.proceed();
        }

        if (requestCount >= properties.maxTries()) {
            throw new RequestLimitException(sessionId);
        } else {
            requestCache.put(sessionId, requestCount + 1);
            return pjp.proceed();
        }
    }

    private String getSession() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new RuntimeException("No request in context");
        }
        return requestAttributes.getSessionId();
    }
}