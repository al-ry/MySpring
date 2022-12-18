package com.spring.configurators.object;

import com.spring.ApplicationContext;

public interface ObjectConfigurator {
    void configure(Object obj, ApplicationContext context);
}
