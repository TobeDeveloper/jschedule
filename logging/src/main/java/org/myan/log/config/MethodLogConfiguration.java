package org.myan.log.config;

import org.aopalliance.aop.Advice;
import org.myan.log.annotation.MethodLog;
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

    private Pointcut pointcut;
    private Advice advice;

    @PostConstruct
    public void init() {
        this.pointcut = new AnnotationMatchingPointcut(null, MethodLog.class);
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
