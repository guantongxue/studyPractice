package com.minjiang.entity;

/**
 * @auther guannw
 * @create 2021/7/22 15:04
 */
public class Ticket {
    //票数
    private int number = 30;
    //操作方法：卖票
    public synchronized void sale(){
        //判断当前是否还有票售卖
        if(number > 0){
            System.out.println(Thread.currentThread().getName()+"：卖出："+number--+"剩下："+number );
        }
    }

}
