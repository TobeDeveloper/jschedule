package org.myan.caching.support;

/**
 * Created by myan on 11/20/2017.
 * Intellij IDEA
 */
public interface CacheObject{

    String getName();

    <T> T get(Class<T> clazz);

    <K, T> void put(Class<K> key, Class<T> clazz);

}
