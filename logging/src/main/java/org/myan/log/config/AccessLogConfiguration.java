package org.myan.log.config;

import ch.qos.logback.classic.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.myan.util.AppUtil;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */

@Configuration
@Aspect
public class AccessLogConfiguration {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AccessLogConfiguration.class);
    private AccessLog log;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    @Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
    public void logPoint() {

    }

    @Before("logPoint()")
    public void logBefore() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        lock.writeLock().lock(); // lock here.
        log = new AccessLog();
        log.setDateStamp(new Date(System.currentTimeMillis()));
        log.setClientIp(AppUtil.getClientIp(request));
        log.setRequestMethod(request.getMethod());
        log.setPath(request.getRequestURI());
    }

    @AfterReturning(returning = "r", pointcut = "logPoint()")
    public void logAfterReturning(Object r) {
        log.setResponseStatus("OK");
        LOGGER.info(log.getLogString() + "Response class: {}", r.getClass().getName());
        lock.writeLock().unlock();
    }

    @AfterThrowing(throwing = "e", pointcut = "logPoint()")
    public void logAfterThrowing(Throwable e) {
        log.setResponseStatus("EXCEPTION");
        LOGGER.error(log.getLogString() + "Exception message: {}", e.getMessage());
        lock.writeLock().unlock();
    }
}
