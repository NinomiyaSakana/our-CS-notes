package com.atguigu.java;

/**
 * 3、使用同步方法实现runnable接口的线程安全问题
 */

class Window3 implements Runnable {
    private int ticket = 100;

    @Override
    public void run() {
        while(true){
            show();
        }

    }

    //不适合用synchronized直接改写run
    public synchronized void show() { //同步监视器：this
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ":卖票，票数为" + ticket);

            ticket--;
        }
    }
}

public class WindowTest3 {
    public static void main(String[] args) {
        Window3 w = new Window3();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();


    }
}
