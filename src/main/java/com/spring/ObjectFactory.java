package com.spring;

import com.spring.annotations.InjectProperty;
import com.spring.configurators.ObjectConfigurator;
import com.spring.testclasses.TestClass;
import com.spring.testclasses.TestInterface;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectFactory {
    private static ObjectFactory INSTANCE = new ObjectFactory();
    private Config config;
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory() {
        this.config = new JavaConfig("com.spring", new HashMap<>(Map.of(TestInterface.class, TestClass.class)));
        for (Class<? extends ObjectConfigurator> objectConfigurator : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(objectConfigurator.getDeclaredConstructor().newInstance());
        }
    }

    public static ObjectFactory getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public  <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T t = implClass.getDeclaredConstructor().newInstance();
        // check for fields from derived classes

        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t));

        return t;
    }
}
