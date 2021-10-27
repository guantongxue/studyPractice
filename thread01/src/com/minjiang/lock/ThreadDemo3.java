package com.minjiang.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther guannw
 * @create 2021/7/26 9:11
 */

//创建资源类
class ShareResource {

    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //打印5次，参数第几轮
    public void print5 (int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            while(flag != 1 ){
                //判断等待
                c1.await();
            }
            for (int i =0 ; i< 5;i++ ) {
                System.out.println(Thread.currentThread().getName()+"::"+i+":轮数:"+loop);
            }
            flag = 2;
            c2.signal();
        }finally {
            lock.unlock();
        }
    }
    //打印10次，参数第几轮
    public void print10 (int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            while(flag != 2 ){
                //判断等待
                c2.await();
            }
            for (int i =0 ; i< 10;i++ ) {
                System.out.println(Thread.currentThread().getName()+"::"+i+":轮数:"+loop);
            }
            flag = 3;
            c3.signal();
        }finally {
            lock.unlock();
        }
    }
    //打印15次，参数第几轮
    public void print15 (int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            while(flag != 3 ){
                //判断等待
                c3.await();
            }
            for (int i =0 ; i < 15;i++ ) {
                System.out.println(Thread.currentThread().getName()+"::"+i+":轮数:"+loop);
            }
            flag = 1;
            c1.signal();
        }finally {
            lock.unlock();
        }
    }




}
public class ThreadDemo3 {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i =0;i <= 10 ; i++){
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"aa").start();
        new Thread(()->{
            for (int i =0;i < 10 ; i++){
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"bb").start();
        new Thread(()->{
            for (int i =0;i < 10 ; i++){
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"cc").start();
    }
}
