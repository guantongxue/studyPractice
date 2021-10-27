package com.minjiang.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther guannw
 * @create 2021/7/26 23:27
 */
public class SyncLockDemo {

    public static void main(String[] args) {
        //lock演示可重入锁
        Lock lock = new ReentrantLock();

        //创建线程
        new Thread(()->{
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"::外层");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+"::中层");
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName()+"::内层");
                    }finally {
                        lock.unlock();
                    }
                }finally {
                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"t1").start();
//        Object o = new Object();
//        new Thread(()->{
//            synchronized (o){
//                System.out.println(Thread.currentThread().getName()+" 外层");
//                synchronized (o){
//                    System.out.println(Thread.currentThread().getName()+" 中层");
//                    synchronized (o){
//                        System.out.println(Thread.currentThread().getName()+" 内层");
//                    }
//                }
//            }
//        },"t1").start();
    }
}
