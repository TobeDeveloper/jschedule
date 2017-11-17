package org.myan.jschedule.web.exception;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
public enum ErrorMessage {
    // system level
    VALIDATION_ERROR("J-101", "Bean validation failed"),
    // business level
    EMAIL_REQUIRED("J-201", "Please supply correct email."),
    EMAIL_REGISTERED("J-202", "This email has been used, please change another one."),
    UNMATCHED_PASSWORD("J-203", "Please confirm entered password."),
    PASSWORD_REQUIRED("J-204", "Please enter your password."),
    NO_SUCH_USER("J-205", "User not exist."),
    INVALID_PASSWORD("J-206", "Incorrect password."),
    // authority level
    LOGIN_REQUIRED("J-301", "Please login first."),
    INVALID_OPERATION("J-302", "Invalid operation."),
    // data level
    EMPTY_RESULT("J-401", "No result matched.");

    private final String errorCode;
    private final String errorMessage;

    ErrorMessage(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
