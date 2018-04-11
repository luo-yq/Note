/**
 * Created by lyt1987 on 15/4/20.
 */
import  groovy.lang.*;
import groovy.util.GroovyScriptEngine;

/**
 * java 调用groovy类时，groovy类中需包含main方法，含有参构造是必须显式声明无参构造
 *
 */

public class TestGroovy {
    /**
     * @param args
     */
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        fun1();
        fun2();
        fun3();
        System.out.print(System.currentTimeMillis() - time);
        System.out.println(" ms");
    }

    private static void fun3() {
        try {
            GroovyScriptEngine engine = new GroovyScriptEngine("");
            Binding binding = new Binding();
            binding.setVariable("language", "Groovy");



            Object value = engine.run("src/ClassTest.groovy", binding);
            assert value.equals("The End");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fun2() {
        Binding binding = new Binding();
        binding.setVariable("x", 10);
        binding.setVariable("language", "Groovy");
        GroovyShell shell = new GroovyShell(binding);
        Object value = shell.evaluate("println \"Welcome to $language\"; y = x * 2; z = x * 3; println x ");
        shell.run("class test{public static void main(String[] args){ println(args[0]) }}","testcls",new String[]{"d"});
        assert value.equals(10);
        assert binding.getVariable("y").equals(20);
        assert binding.getVariable("z").equals(30);
    }

    private static void fun1() {
        GroovyClassLoader loader = new GroovyClassLoader();
        try {
            String scriptText = "class Foo {\n"
                    + "  int add(int x, int y) { x + y }}";
            Class<?> newClazz = loader.parseClass(scriptText);
            Object obj = newClazz.newInstance();
            Object i = obj.getClass().getMethod("add", int.class, int.class)
                    .invoke(obj, 23, 3);

            String user="user";


            String scripts="class Foo{\n" +
                    "  String getUser(){\n" +
                    "def user='$user,\"222\",\"333\"' \n" +
                    "return user \n" +
                    "}" +
                    "}";
            Class<?> getUserCls = loader.parseClass(scripts);
            GroovyObject getUser = (GroovyObject)getUserCls.newInstance();
            Object users = getUser.invokeMethod("getUser",  null);


            System.out.println(i);
            System.out.println(users);
            obj = newClazz.newInstance();
            i = obj.getClass().getMethod("add", int.class, int.class)
                    .invoke(obj, 23, 3);
            System.out.println(i);
            users = getUser.invokeMethod("getUser",  null);
            System.out.println(users);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


