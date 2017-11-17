package org.myan.jschedule.web.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
public class DefaultResponseEntry {
    public static DefaultResponseEntry list() {
        return new DefaultResponseEntry(null, null);
    }

    private Object defaultValue;
    private String name;

    private Map<String, Object> namedValues = new HashMap<>();

    private DefaultResponseEntry(String name, Object value) {
        this.defaultValue = value;
    }

    public DefaultResponseEntry setName(String name) {
        this.name = name;
        if (!namedValues.containsKey(name))
            namedValues.put(name, Collections.EMPTY_LIST);
        return this;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getNamedValues() {
        return namedValues;
    }
}
