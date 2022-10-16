package com.spring;

import com.spring.testclasses.TestClass;
import com.spring.testclasses.TestInterface;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {
    private static ObjectFactory INSTANCE = new ObjectFactory();
    private Config config;

    public ObjectFactory() {
        this.config = new JavaConfig("com.spring", new HashMap<>(Map.of(TestInterface.class, TestClass.class)));
    }

    public static ObjectFactory getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public  <T> T getInstance(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T t = implClass.getDeclaredConstructor().newInstance();

        return t;
    }
}
