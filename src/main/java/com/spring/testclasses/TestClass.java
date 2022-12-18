package com.spring.testclasses;

import com.spring.ObjectFactory;
import com.spring.annotations.InjectByType;
import com.spring.annotations.Singleton;

@Singleton
@Deprecated
public class TestClass implements TestInterface {
    private static final String field = "some info";

    @InjectByType
    private InjectedClass injClass;

    @Override
    public String test() {
        injClass.printInjectedValue();
        return field;
    }
}
