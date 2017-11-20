package org.myan.caching;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by myan on 11/20/2017.
 * Intellij IDEA
 */
@Component
public class AccountCacheManager<K, T> {
    private final Map<K, T> cache = new ConcurrentHashMap<>();

    public T get(K key) {
        if(cache.containsKey(key))
            return cache.get(key);
        return null;
    }

    public void updateCache(K key, T value) {
        cache.put(key, value);
    }

    public void deleteCache(K key) {
        if(cache.containsKey(key))
            cache.remove(key);
    }

    public void evictAll() {
        cache.clear();
    }
}
