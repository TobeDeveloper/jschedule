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
@Configuration
public class MethodLogConfiguration extends AbstractPointcutAdvisor {
    private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(MethodLogConfiguration.class);
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

    private class MethodLogInterceptor implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            String methodName = invocation.getMethod().getName();
            long start = System.currentTimeMillis();
            Object result = invocation.proceed();
            long end = System.currentTimeMillis();
            LOGGER.info("**Method log:({}), time cost:({})", methodName, (end - start));
            return result;
        }
    }

}
