import com.spring.Application;
import com.spring.ApplicationContext;
import com.spring.testclasses.TestClass;
import com.spring.testclasses.TestInterface;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //ApplicationContext application = Application.run("com.spring", new HashMap<>(Map.of(TestInterface.class, TestClass.class)));
        String str = "";
        String str1 = new String("").intern();
        if (str == str1) {
            System.out.println("test");
        }
    }

}
