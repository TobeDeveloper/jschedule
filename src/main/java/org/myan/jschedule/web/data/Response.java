package org.myan.jschedule.web.data;

import java.util.Collection;
import java.util.Map;

/**
 * Created by myan on 11/17/2017.
 * Intellij IDEA
 */
public abstract class Response<T> {

    protected Header<T> headers;

    public void setResponseCode(String responseCode) {
        if (headers == null)
            headers = new Header<>();
        headers.setResponseCode(responseCode);
    }

    public void setResponseMessage(String responseMessage) {
        if (headers == null)
            headers = new Header<>();
        headers.setResponseMessage(responseMessage);
    }

    public abstract void putObject(String name, Object value);

    public abstract void putCollection(String name, Collection<T> list);

    public abstract void putArray(String name, Object[] array);

    public abstract void putMap(String name, Map<String, T> map);

}
