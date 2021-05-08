package com.atguigu.java;

/**
 * 1、使用同步代码块实现Runnable接口的线程安全问题
 */

class Window1 implements Runnable{
    private int ticket=100;

    //Object obj=new Object();

    @Override
    public void run() {
        while(true){
            //window1的对象是this 是唯一的，所以这把锁可以实现
            //方式二： synchronized
            synchronized (this){
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


public class WindowTest1 {

    public static void main(String[] args) {
        Window1 w= new Window1();

        Thread t1= new Thread(w);
        Thread t2= new Thread(w);
        Thread t3= new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();


    }



}
