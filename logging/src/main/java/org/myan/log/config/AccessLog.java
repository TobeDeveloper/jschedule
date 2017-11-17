package org.myan.log.config;

import java.util.Date;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
public class AccessLog {
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

    public String getLogString() {
        return String.format("[System access log]: %s-IP:%s, request method: %s, path: %s, response status: %s\r\n",
                dateStamp.toString(), clientIp, requestMethod.toUpperCase(), path, responseStatus);
    }
}
