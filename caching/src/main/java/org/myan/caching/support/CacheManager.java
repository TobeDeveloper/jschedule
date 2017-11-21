package org.myan.caching.support;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by myan on 11/20/2017.
 * Intellij IDEA
 */
public class CacheManager {
    private CacheManager() {

    }

    public void updateCache(String name, Object key, Object result) {
        synchronized (this.cacheMap) {
            Set<Cache> cacheObjects = cacheMap.get(name);
            Cache cache = new Cache(key, result);
            if(cacheObjects == null){
                cacheMap.put(name, new LinkedHashSet<>());
                cacheMap.get(name).add(cache);
            } else {
                cacheObjects.add(cache);
            }
        }
    }

    public void reloadCache(String name) {
        synchronized (this.cacheMap) {
            if(this.cacheMap.containsKey(name))
                cacheMap.remove(name);
        }
    }

    public static class InstanceHolder{
        private final static CacheManager INSTANCE = new CacheManager();

        public static CacheManager getINSTANCE() {
            return INSTANCE;
        }
    }

    private final Map<String, Set<Cache>> cacheMap = new ConcurrentHashMap<>();

    public Cache getCacheObject(String name, Object key) {
        Set<Cache> caches = this.cacheMap.get(name);
        if(caches != null) {
            for (Cache cache : caches) {
                if(cache.getKey() instanceof Integer &&
                        cache.getKey().equals(key)){
                    return cache;
                }
            }
        }
        return null;
    }

}
