package com.minjiang.lock;

import com.minjiang.entity.LTicket;

/**
 * @auther guannw
 * @create 2021/7/23 14:00
 */


public class LSaleTicket {



    public static void main(String[] args) {
        LTicket ticket = new LTicket();
        new Thread(()->{
            for (int i = 0 ; i < 1000 ;i++){
                ticket.sale();
            }
        },"aa").start();

        new Thread(()->{
            for (int i = 0 ; i < 1000 ;i++){
                ticket.sale();
            }
        },"bb").start();

        new Thread(()->{
            for (int i = 0 ; i <1000 ;i++){
                ticket.sale();
            }
        },"cc").start();


    }

}
