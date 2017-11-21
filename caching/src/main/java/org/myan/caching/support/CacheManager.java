package org.myan.caching.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by myan on 11/20/2017.
 * Intellij IDEA
 */
public class CacheManager {
    private CacheManager() {

    }

    public void updateCache(String name, Object result) {
        synchronized (this.cacheMap) {

            cacheMap.put(name, new CacheObject() {
                @Override
                public String getName() {
                    return name;
                }

                @Override
                @SuppressWarnings("unchecked")
                public <T> T get(Class<T> clazz) {
                    return (T) result;
                }

                @Override
                public <K, T> void put(Class<K> key, Class<T> clazz) {

                }
            });
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

    private final Map<String, CacheObject> cacheMap = new ConcurrentHashMap<>();

    public CacheObject getCacheObject(String name) {
        CacheObject object = this.cacheMap.get(name);
        if(object != null) {
            return object;
        }
        return null;
    }


}
