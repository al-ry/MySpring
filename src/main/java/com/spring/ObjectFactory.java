package com.spring;

import com.spring.configurators.object.ObjectConfigurator;
import com.spring.configurators.proxy.ProxyConfigurator;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {
    private final ApplicationContext context;
    private List<ObjectConfigurator> objectConfigurators = new ArrayList<>();
    private List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends ObjectConfigurator> objectConfigurator : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            objectConfigurators.add(objectConfigurator.getDeclaredConstructor().newInstance());
        }
        for (Class<? extends ProxyConfigurator> proxyConfigurator : context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            proxyConfigurators.add(proxyConfigurator.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        T t = create(implClass);
        // check for fields from derived classes

        configureObject(t);

        postInit(t);


        // todo deprecated only for class, need for method
        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            t = (T)proxyConfigurator.useProxyIfNeeded(t, implClass);
        }

        return t;
    }

    @SneakyThrows
    private <T> void postInit(T t) {
        for (Method method : t.getClass().getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(t);
            }
        }

    }

    private <T> void configureObject(T t) {
        objectConfigurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private static <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        T t = implClass.getDeclaredConstructor().newInstance();
        return t;
    }
}
