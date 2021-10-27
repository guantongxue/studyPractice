package com.minjiang.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther guannw
 * @create 2021/7/26 8:43
 */

//第一步创建资源类
class Share {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //+1
    public void incr() throws InterruptedException {
        lock.lock();
        try {
            while(number != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"::"+number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    //-1
    public void decr() throws InterruptedException {
        lock.lock();
        try {
            while(number != 1){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"::"+number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

}

public class ThreadDemo2 {

    public static void main(String[] args) {

        Share share = new Share();
        new Thread(()->{
            for (int i = 0;i < 10 ;i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"aa").start();

        new Thread(()->{
            for (int i = 0;i < 10 ;i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"bb").start();
        new Thread(()->{
            for (int i = 0;i < 10 ;i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"cc").start();

        new Thread(()->{
            for (int i = 0;i < 10 ;i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"dd").start();
    }

}
