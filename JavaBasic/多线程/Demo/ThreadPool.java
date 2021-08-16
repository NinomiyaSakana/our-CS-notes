package com.atguigu.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建线程的方式四：使用线程池
 *
 */


class NumberThread implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

class NumberThread1 implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i < 100; i++) {
            if (i % 2 == 1) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}


public class ThreadPool {

    public static void main(String[] args) {

        //里面有10个线程
        //1、提供指定数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1=(ThreadPoolExecutor)service;

        //设置线程池属性
        service1.setCorePoolSize(15);
        //service1.setKeepAliveTime(); 活跃时间
        //service.getClass()可以获取类对象

        //2、执行制定的线程的操作。需要提供实现runnable接口或callable接口实现类的对象
        service.execute(new NumberThread());//没有返回值
        service.execute(new NumberThread1());//没有返回值
        //service.submit(); 适合使用在callable，捕捉返回值

        service.shutdown();
        //3、关闭连接池

    }
}
