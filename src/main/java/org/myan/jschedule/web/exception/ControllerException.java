package org.myan.jschedule.web.exception;

import org.myan.jschedule.web.data.DefaultResponseEntry;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
public class ControllerException extends BasicException {
    private DefaultResponseEntry[] entries;

    public ControllerException(ErrorMessage message) {
        super(message);
    }

    public ControllerException(ErrorMessage message, DefaultResponseEntry... entries) {
        super(message);
        this.entries = entries;
    }

    public DefaultResponseEntry[] getEntries() {
        return entries;
    }
}
