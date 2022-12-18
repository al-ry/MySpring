package com.spring;

import java.util.Map;


public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> ifc2ImpClass) {
        JavaConfig config = new JavaConfig(packageToScan, ifc2ImpClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setObjectFactory(objectFactory);
        //todo - init all singletons which are not lazy
        return context;
    }
}
