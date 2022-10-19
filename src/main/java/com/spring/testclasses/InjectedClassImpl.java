package com.spring.testclasses;

import com.spring.annotations.InjectProperty;

public class InjectedClassImpl implements InjectedClass {
    @InjectProperty
    private String injectedField;

    @Override
    public void printInjectedValue() {
        System.out.println(injectedField);
    }
}
