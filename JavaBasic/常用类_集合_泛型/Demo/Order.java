package com.atguigu.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义泛型类
 *
 * @param <T>
 */

public class Order<T> {

    String orderName;
    int orderId;

    //类的内部结构可以使用类的泛型
    //可以想像成有一个类叫做T，创建了一个对象
    T orderT;


    //下面都不是泛型方法
    public Order() {
        //T[] arr=new T[10];
        //只能这么写
        T[] arr = (T[]) new Object[10];
    }

    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }


    //静态方法中不能用泛型
    //静态结构遭遇对象的创建
//    public static void show(T orderT){
//        System.out.println(orderT);
//
//    }

    //泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系
    //换句话说，泛型方法所属的类是不是泛型类都没关系。
    //在前面加<>告诉编译器E不是一个类，是泛型。
    //泛型方法可以声明为静态的，因为泛型参数实在调用方法时确定的，不在实例化类的时候确定。
    public <E> List<E> copyFromArrayToList(E[] arr){
        ArrayList<E> list=new ArrayList<>();

        for(E e:arr){
            list.add(e);
        }
        return list;

    }
}
