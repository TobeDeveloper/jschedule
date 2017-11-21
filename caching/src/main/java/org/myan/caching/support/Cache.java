package org.myan.caching.support;

/**
 * Created by myan on 11/21/2017.
 * Intellij IDEA
 */
public class Cache {
    private Object key;
    private Object value;

    public Cache(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
