package org.myan.caching.support;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by myan on 11/20/2017.
 * Intellij IDEA
 */
public class CacheInterceptor implements MethodInterceptor {
    private final Logger logger = LoggerFactory.getLogger(CacheInterceptor.class);
    private final CacheManager cacheManager = CacheManager.InstanceHolder.getINSTANCE();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();// the target method we want to enhance.
        Cacheable cacheable = method.getAnnotation(Cacheable.class);
        CacheObject cache = cacheManager.getCacheObject(cacheable.value());
        if(cache != null) {
            logger.info("Get data from cache...");
            return cache.get(method.getReturnType());
        } else {
            Object result = invocation.proceed();
            // add the result to manager.
            lock.readLock().lock();
            cacheManager.updateCache(cacheable.value(), result);
            lock.readLock().unlock();
            return result;
        }
    }

}

@Configuration
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
