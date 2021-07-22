package www.atguitu.com;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {

    //反射之前对于person类的操作
    @Test
    public void test1() {

        //1、创建person类的对象
        Person p1 = new Person("Tom", 12);

        //2、通过对象调用内部属性和方法,修改
        p1.age = 10;
        System.out.println(p1.toString());

        p1.show();

        //在person类的外部，是不可以通过person类的对象调用私有结构的

    }

    //有了反射之后
    @Test
    public void test2() throws Exception {

        //1、通过反射。创建Person类的对象
        Class clazz = Person.class;
        Constructor cons = clazz.getConstructor(String.class, int.class);

        Object obj = cons.newInstance("Tom", 12);
        Person p = (Person) obj;
        System.out.println(p.toString());

        //2、通过反射，调用对象指定的属性、方法
        //调用属性
        Field age = clazz.getDeclaredField("age");
        age.set(p, 10);
        System.out.println(p.toString());

        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        //通过反射，可以调用Person类的死哟结构，比如私有的构造器和方法、属性
        //调用私有的构造器
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p1 = (Person) cons1.newInstance("Jerry");
        System.out.println(p1);

        //调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);//改变orivate
        name.set(p1, "hanmeimei");

        //调用私有的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p1, "china"); //相当于p1.showNation("china")
        System.out.println(nation);

    }

    //疑问1：直接new或者反射的方式都可以调用公共结构没那么开发中调用哪个？
    //建议直接用new的方式。
    //疑问2：反射机制与面向对象中的封装性是不是矛盾的？
    //如何看待？
    //不矛盾。封装性：建议调什么；反射：什么能调什么不能调。

    //关于java.lang.Class类的理解(见笔记)

    //获取Class的实例的方式

    @Test
    public void test3() throws ClassNotFoundException {
        //方式一：
        Class<Person> clazz1 = Person.class;
        //Class clazz1=Person.class; 删掉泛型也一样
        System.out.println(clazz1);

        //方式二：通过运行时类的对象，调用getClass()
        Person p1 = new Person();
        Class clazz2 = p1.getClass(); //获取对象，看看是哪个class造的
        System.out.println(clazz2);

        //方式三：调用Class的静态方法：forName（String classPath）
        //该方式用的比较多
        Class clazz3 = Class.forName("www.atguitu.com.Person");
        //clazz3=Class.forName("java.lang.String");  //非自己定义的类也可以
        System.out.println(clazz3);

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);
        //获取方式不同，但是都是同一个类（true）
        //家在到内存中的运行时类，会缓存一段时间。再次时间之内可以通过不同的方式获得它。
        //前三个需要掌握

        //方式四（了解）
        //使用类加载器ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("www.atguitu.com.Person");
        System.out.println(clazz4);

        System.out.println(clazz1==clazz4);


    }


}
