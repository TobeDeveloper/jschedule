package org.myan.log.config;

import ch.qos.logback.classic.Logger;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.LoggerFactory;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
class MethodLogInterceptor implements MethodInterceptor {
    private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(MethodLogInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();
        LOGGER.info("[Method log]:{}, time cost:{} ms", methodName, (end - start));
        return result;
    }
}
