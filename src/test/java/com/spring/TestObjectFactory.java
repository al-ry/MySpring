package com.spring;

import com.spring.testclasses.TestClass;
import com.spring.testclasses.TestInterface;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestObjectFactory {

    @Test
    public void test() {
        ApplicationContext application = Application.run("com.spring", new HashMap<>(Map.of(TestInterface.class, TestClass.class)));
        TestInterface object = application.getObject(TestInterface.class);

        application.getObject(TestInterface.class);
        Assert.assertEquals("some info", object.test());
    }
}
