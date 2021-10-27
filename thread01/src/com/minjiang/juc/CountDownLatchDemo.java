package com.minjiang.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @auther guannw
 * @create 2021/7/30 12:25
 */


public class CountDownLatchDemo {

    //6个同学陆续离开教室，班长锁门
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);
        // 6个同学陆续离开教室之后
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 号同学离开了教室");
                //计数-1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //自旋等待
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+" 班长锁门走人了");
    }
}
