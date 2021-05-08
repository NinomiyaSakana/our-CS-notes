package com.atguigu.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式三：实现callable接口
 * jdk5.0新增的方法
 */


//1、创建一个callable接口的实现类
class NumThread implements Callable {
    //2、实现call方法，把实现操作写在call中

    //可以抛异常
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        //多态 把int转化成了object类型的integer形式
        return sum;
    }
}

public class ThreadNew {

    public static void main(String[] args) {
        //3、创建callable接口实现类的对象
        NumThread numThread = new NumThread();
        //4、将次对象传递到futureTask构造器中，创建futureTask对象
        FutureTask futureTask = new FutureTask(numThread);
        //5、将futureTask的对象作为参数粗汉地道thread类的构造器中，创建thread对象并调用start
        new Thread(futureTask).start();

        try {
            //6、获取callable中call方法的返回值
            //get方法的返回值是futureTask的构造器参数callable实现类重写的call（）返回值
            //call的return
            //不要返回值就不用调用get
            Object sum = futureTask.get();
            System.out.println("总和为："+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
