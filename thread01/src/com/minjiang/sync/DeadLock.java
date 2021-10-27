package com.minjiang.sync;

/**
 * @auther guannw
 * @create 2021/7/28 9:18
 */
public class DeadLock {

    private static Object a = new Object();
    private static Object b = new Object();
    public static void main(String[] args) {

        new Thread(()->{
            synchronized (a){
                System.out.println(Thread.currentThread().getName()+"持有锁a,试图获取锁b");
                synchronized (b){
                    System.out.println(Thread.currentThread().getName()+"获取锁b");
                }

            }
        },"a").start();

        new Thread(()->{
            synchronized (b){
                System.out.println(Thread.currentThread().getName()+"持有锁b,试图获取锁a");
                synchronized (a){
                    System.out.println(Thread.currentThread().getName()+"获取锁b");
                }

            }
        },"b").start();

    }
}
