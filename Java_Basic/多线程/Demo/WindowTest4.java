package com.atguigu.java;

/**
 * 4、使用同步方法实现Thread继承后的线程安全问题
 * 关于同步方法的总结：
 * 1、同步方法仍然设计同步监视器。只是不需要显式地声明
 * 2、非静态的同步方法，同步监视器是this
 *  静态的同步方法，同步监视器是当前类本身
 */

class Window4 extends Thread {
    private static int ticket = 100;


    //非静态可以调用静态
    @Override
    public void run() {
        while(true){
            show();
        }

    }

    //把方法写成静态
    //private synchronized void show(){ //同步监视器 t1 t2 t3 此种解决方式不行
    private static synchronized void show(){ //现在的同步监视器：不是this 是当前的类Window4（唯一）
            if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":卖票，票号为" + ticket);

            ticket--;
        }

    }
}


public class WindowTest4 {
    public static void main(String[] args) {
        Window2 t1 = new Window2();
        Window2 t2 = new Window2();
        Window2 t3 = new Window2();


        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();


    }
}
