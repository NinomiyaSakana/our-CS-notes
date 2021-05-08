package com.atguigu.java;

/**
 * 2、使用同步代码块实现Thread继承后的线程安全问题
 */

class Window2 extends Thread {
    private int ticket = 100;

    private  static Object obj= new Object();
    //加上static为了让其成为共享数据
    //因为有三个对象

    @Override
    public void run() { //同步监视器：this
        while(true){
            //synchronized (this)不对
            //因为这里有3个window2实现的对象
            //新方法
            //synchronized (Window2.class) 创建window2的对象
            //类也是对象
            synchronized (obj){
                if (ticket > 0) {


                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":卖票，票号为" + ticket);

                    ticket--;
                }else{
                    break;
                }

            }



        }

    }
}

public class WindowTest2 {
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
