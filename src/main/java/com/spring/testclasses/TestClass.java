package com.spring.testclasses;

import com.spring.ObjectFactory;

public class TestClass implements TestInterface {
    private static final String field = "some info";
    private InjectedClass injClass = ObjectFactory.getInstance().createObject(InjectedClass.class);

    @Override
    public String test() {
        injClass.printInjectedValue();
        return field;
    }
}
