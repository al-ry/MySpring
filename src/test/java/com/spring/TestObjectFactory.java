package com.spring;

import com.spring.testclasses.TestInterface;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestObjectFactory {

    @Test
    public void test() {
        TestInterface instance = ObjectFactory.getInstance().getInstance(TestInterface.class);
        Assert.assertEquals("some info", instance.test());
    }
}
