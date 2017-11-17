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
import java.sql.Date;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */

@Configuration
@Aspect
public class AccessLogConfiguration {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AccessLogConfiguration.class);
    private AccessLog log;

    @Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
    public void logPoint() {

    }

    @Before("logPoint()")
    public void logBefore() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.setDateStamp(new Date(System.currentTimeMillis()));
        log.setClientIp(AppUtil.getClientIp(request));
        log.setRequestMethod(request.getMethod());
        log.setPath(request.getRequestURI());
        log = new AccessLog();
    }

    @AfterReturning(returning = "r", pointcut = "logPoint()")
    public void logAfterReturning(Object r) {
        log.setResponseStatus("OK");
        LOGGER.info(log.toString() + "Response class: {}", r.getClass().getName());
    }

    @AfterThrowing(throwing = "e", pointcut = "logPoint()")
    public void logAfterThrowing(Throwable e) {
        log.setResponseStatus("EXCEPTION");
        LOGGER.error(log.toString() + "Exception message: {}", e.getMessage());
    }

    private class AccessLog {
        private Date dateStamp;
        private String clientIp;
        private String requestMethod;
        private String path;
        private String responseStatus;

        void setDateStamp(Date dateStamp) {
            this.dateStamp = dateStamp;
        }

        void setClientIp(String clientIp) {
            this.clientIp = clientIp;
        }

        void setRequestMethod(String requestMethod) {
            this.requestMethod = requestMethod;
        }

        void setPath(String path) {
            this.path = path;
        }

        void setResponseStatus(String responseStatus) {
            this.responseStatus = responseStatus;
        }

        @Override
        public String toString() {
            return String.format("[System access log]: %s-IP:%s, request method: %s, path: %s, response status: %s\r\n",
                    dateStamp.toString(), clientIp, requestMethod.toUpperCase(), path, responseStatus);
        }
    }
}
