package com.spring.testclasses;

public class TestClass implements TestInterface {
    private static final String field = "some info";

    @Override
    public String test() {
        return field;
    }
}
