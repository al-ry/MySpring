package com.spring.configurators.proxy;

public interface ProxyConfigurator {
    Object useProxyIfNeeded(Object t, Class implClass);
}
