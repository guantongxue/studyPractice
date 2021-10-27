package com.minjiang.juc;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @auther guannw
 * @create 2021/8/1 23:00
 */

//6辆汽车，3个停车位
public class SemaphoreDemo {

    public static void main(String[] args) {
        //设置Semaphore,设置许可数量
        Semaphore semaphore = new Semaphore(3);
        //模拟6辆汽车
        for (int i = 0 ; i <6 ;i++){
            new Thread(()->{
                try {

                    //抢占车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+" 抢到了车位");
                    //设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName()+" ----离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
