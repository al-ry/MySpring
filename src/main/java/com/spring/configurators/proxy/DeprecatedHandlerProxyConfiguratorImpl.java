package com.spring.configurators.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DeprecatedHandlerProxyConfiguratorImpl implements ProxyConfigurator {
    @Override
    public Object useProxyIfNeeded(Object t, Class implClass) {
        if ( implClass.isAnnotationPresent(Deprecated.class)) {
            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), (proxy, method, args) -> {
                System.out.println("invokation");
                return method.invoke(t);
            });
        } else {
            return t;
        }
    }
}
