package com.minjiang.entity;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther guannw
 * @create 2021/7/23 14:01
 */
public class LTicket {

    //票数量

    private int number = 1000;

    //创建可重入锁
    private final ReentrantLock lock = new ReentrantLock();
    //卖票方法
    public void sale(){
        //上锁
         lock.lock();
         try{
             //判断是否有票
             if(number > 0){
                 System.out.println(Thread.currentThread().getName()+"::卖出"+(number--)+"剩余:"+number);

             }
         }finally {
             //解锁
             lock.unlock();
         }


    }
}
