package com.minjiang.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @auther guannw
 * @create 2021/7/31 13:30
 */
public class CyclicBarrierDemo {

    //创建固定值
    private static final int NUMBER = 7;
    public static void main(String[] args) {

        //创建CyclicBarrier对象
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER,()->{
            System.out.println("等待7个线程就可以执行该方法");
        });
        //等待7个线程的过程
        for (int i = 0 ; i < 7 ; i++){
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+" 号线程");
                    //等待
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
