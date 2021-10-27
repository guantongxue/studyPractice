package com.minjiang.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther guannw
 * @create 2021/8/23 9:13
 */

//演示线程池三种常用分类
public class ThreadPoolDemo1 {

    public static void main(String[] args) {
        //一池N线程
//        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
//        try{
//            //10个顾客请求
//            for (int i = 0 ; i < 10 ; i++){
//                threadPool1.execute(() ->{
//                    System.out.println(Thread.currentThread().getName()+" 办理业务");
//                });
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            threadPool1.shutdown();
//        }

        //一池一线程
//        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
//
//        try {
//            for (int i = 0 ; i < 10 ; i++){
//                threadPool2.execute(()->{
//                    System.out.println(Thread.currentThread().getName()+" 办理业务");
//                });
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            threadPool2.shutdown();
//        }

        //可扩容线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        try {
            for (int i = 0 ; i < 10 ; i++ ){
                threadPool3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool3.shutdown();
        }

    }
}
