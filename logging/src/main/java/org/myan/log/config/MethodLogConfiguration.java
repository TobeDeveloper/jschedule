package org.myan.log.config;

import ch.qos.logback.classic.Logger;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.myan.log.annotation.MethodLog;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by myan on 11/16/2017.
 * Intellij IDEA
 */
class MethodLogInterceptor implements MethodInterceptor {
    // FIXME use inject logger instead of new intance
    private static final Logger logger = (Logger) LoggerFactory.getLogger(MethodLogInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();
        logger.info("**Method log:({}), time cost:({})", methodName, (end - start));
        return result;
    }
}

@Configuration
public class MethodLogConfiguration extends AbstractPointcutAdvisor{
    private Pointcut pointcut;
    private Advice advice;

    @PostConstruct
    public void init() {
        this.pointcut = new AnnotationMatchingPointcut(MethodLog.class);
        this.advice = new MethodLogInterceptor();
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
