package org.myan.caching.support;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

/**
 * Created by myan on 11/20/2017.
 * Intellij IDEA
 */
public class CacheInterceptor implements MethodInterceptor {
    private final CacheManager cacheManager = CacheManager.InstanceHolder.getINSTANCE();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();// the target method we want to enhance.
        Cacheable cacheable = method.getAnnotation(Cacheable.class);
        CacheObject cache = cacheManager.getCacheObject(cacheable.value());
        if(cache != null) {
            return cache.get(method.getReturnType());
        } else {
            Object result = invocation.proceed();
            // add the result to manager.
            cacheManager.updateCache(cacheable.value(), result);
            return result;
        }
    }

}

@Component
class CachePointcutAdvisor extends AbstractPointcutAdvisor{
    private Pointcut pointcut;
    private Advice advice;

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @PostConstruct
    public void init() {
        this.pointcut = new AnnotationMatchingPointcut(null, Cacheable.class);
        this.advice = new CacheInterceptor();
    }
}
