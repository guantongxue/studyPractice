package com.minjiang.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @auther guannw
 * @create 2021/8/23 15:12
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步调用没有返回值的
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() +" completableFuture1");
        });
        completableFuture.get();
        //异步调用有返回值的
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() +" completableFuture2");
            return 1024;
        });
        completableFuture2.whenComplete((t,u)->{
            System.out.println("-------t="+t);//返回值
            System.out.println("-------u="+u);//异常信息
        });
    }
}
