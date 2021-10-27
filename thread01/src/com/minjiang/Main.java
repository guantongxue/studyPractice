package com.minjiang;

/**
 * @auther guannw
 * @create 2021/7/21 22:42
 */
public class Main {

    public static void main(String[] args) {
        Thread aa = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while(true) {

            }
        }, "aa");
        //设置为守护线程
        aa.setDaemon(true);
        aa.start();

        System.out.println(Thread.currentThread().getName()+"over");
    }
}
