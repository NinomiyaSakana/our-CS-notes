package com.atguigu.java;

import org.junit.Test;

import javax.management.OperationsException;
import java.util.List;

/**
 * 关于自定义泛型类（接口）；
 */

public class GenericTest {

    @Test
    public void test1() {
        //如果定义了泛型类，实例化并没有知名类的泛型
        //则认为这个泛型类为object类型
        //不建议这么写。建议实例化时指定类的泛型
        Order order = new Order();
        order.setOrderT(123);
        order.setOrderT("ABC");


        //指明泛型类型
        Order<String> order1 = new Order<>("orderAA", 1001, "discription");
        order1.setOrderT("AA:discription");
        //下面的方法和collection一样
    }

    @Test
    public void test2() {
        //由于子类在继承带泛型的父类时，指明了泛型类型，那么实例化的时候不用指明了。
        //subOrder普通类
        //因为继承的时候后面没写<>，所以不用指明
        SubOrder sub1 = new SubOrder();
        sub1.setOrderT(1122);

        SubOrder1<String> sub2 = new SubOrder1<>();
        sub2.setOrderT("Order");

    }


    //测试泛型方法
    @Test
    public void test3() {
        Order<String> order=new Order<>();
        Integer[] arr=new Integer[]{1,2,3,4};
        //泛型方法在调用时指明泛型参数的类型
        //与类的泛型没关系
        List<Integer> list=order.copyFromArrayToList(arr);

        System.out.println(list);
    }


}
