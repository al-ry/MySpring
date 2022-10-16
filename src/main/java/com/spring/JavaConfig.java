package com.spring;

import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class JavaConfig implements Config {

    private Reflections scanner;
    private Map<Class, Class> intrfc2ImplClass;

    public JavaConfig(String packageToScan, Map<Class, Class> intrfc2ImplClass) {
        this.scanner = new Reflections(packageToScan); //hardcoded for now
        this.intrfc2ImplClass = intrfc2ImplClass;
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> type) {
        return intrfc2ImplClass.computeIfAbsent(type, aClass -> {
            Set<Class<? extends T>> subTypesOf = scanner.getSubTypesOf(type);
            if (subTypesOf.size() != 1) {
                throw new RuntimeException(type + " has 0 or more than one implementation. Please update your config");
            }
            return subTypesOf.iterator().next();
        });
    }
}
