package org.myan.jschedule.web.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
@JsonPropertyOrder({"headers", "body"})
public class HttpResponse<T> extends Response<T> {
    private Map<String, Object> body = new Hashtable<>();

    public HttpResponse() {
        headers = new Header<>();
    }

    @Override
    public void putObject(String name, Object value) {
        body.put(name, value);
    }

    @Override
    public void putCollection(String name, Collection<T> list) {
        this.putObject(name, list);
    }

    @Override
    public void putArray(String name, Object[] array) {
        this.putObject(name, array);
    }

    @Override
    public void putMap(String name, Map<String, T> map) {
        this.putObject(name, map);
    }
}
