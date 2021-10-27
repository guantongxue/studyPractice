package com.minjiang.sync;

import com.minjiang.entity.Ticket;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther guannw
 * @create 2021/7/22 15:03
 */

//第一步创建资源类，定义属性和操作方法
public class SaleTicket {
    //第二步 创建多个线程 调用资源类操作方法
    public static void main(String[] args) {
        //创建ticket对象
        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for ( int i = 0; i < 40 ; i++){
                    ticket.sale();

                }
            }
        },"aa").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for ( int i = 0; i < 40 ; i++){
                    ticket.sale();
                }
            }
        },"bb").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for ( int i = 0; i < 40 ; i++){
                    ticket.sale();
                }
            }
        },"cc").start();
    }

}
