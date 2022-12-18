package com.spring;

import com.spring.annotations.Singleton;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private Map<Class, Object> objectsCache = new ConcurrentHashMap<>();
    @Setter
    private ObjectFactory objectFactory;
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type) {
        return (T) objectsCache.computeIfAbsent(type, aClass -> {
            Class<? extends T> implClass = type;
            if (type.isInterface()) {
                implClass = config.getImplClass(type);
            }
            T object = (T)objectFactory.createObject(implClass);
            if (aClass.isAnnotationPresent(Singleton.class)) {
                objectsCache.put(aClass, object);
            }
            return object;
        });
    }

    public Config getConfig() {
        return config;
    }
}
