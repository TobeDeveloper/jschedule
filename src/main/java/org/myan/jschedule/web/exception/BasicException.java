package org.myan.jschedule.web.exception;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
public class BasicException extends RuntimeException {

    protected String errorCode;

    public BasicException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public BasicException(ErrorMessage message) {
        this(message.getErrorCode(), message.getErrorMessage());
    }

    public BasicException(Throwable th, ErrorMessage message) {
        super(message.getErrorMessage(), th);
        this.errorCode = message.getErrorCode();
    }

    public String getErrorCode() {
        return errorCode;
    }
}
