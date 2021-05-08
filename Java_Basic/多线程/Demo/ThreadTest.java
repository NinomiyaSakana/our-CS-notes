package com.atguigu.java;

import sun.lwawt.macosx.CSystemTray;

/**
 * 谨慎执行，是错误的代码
 * 演示线程的死锁
 * 使用同步时要避免死锁
 *
 */


public class ThreadTest {
    public static void main(String[] args) {

        //有可能先执行线程一。也有可能先执行线程二
        //加入sleep是为了让他死锁得更容易些
        //程序未响应


        //两把锁
        StringBuffer s1=new StringBuffer();
        StringBuffer s2=new StringBuffer();

        //线程1
        new Thread(){
            @Override
            public void run() {
                synchronized (s1){
                    s1.append("a");
                    s2.append("1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s2){
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }

            }
        }.start();



        //线程2
        new Thread(new Runnable(){
            @Override
            public void run() {
                synchronized (s2){
                    s1.append("c");
                    s2.append("3");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s1){
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }

            }
        }).start();

    }



}
