package com.spring.configurators.object;

import com.spring.ApplicationContext;
import com.spring.annotations.InjectByType;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class InjectByTypeAnnotationObjectConfiguratorImpl implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object obj, ApplicationContext context) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent((InjectByType.class))) {
                field.setAccessible(true);
                field.set(obj, context.getObject(field.getType()));
            }
        }
    }
}
