package com.minjiang.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @auther guannw
 * @create 2021/7/28 10:08
 */

class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable{

    @Override
    public Integer call() throws Exception {
        return 200;
    }
}
public class Demo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //使用Runnable线程
        //new Thread(new MyThread1(),"AA").start();

        //使用Callable
        FutureTask futureTask1 = new FutureTask(new MyThread2());

        FutureTask<Integer> futureTask2 = new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName()+" come in callable");
           return 1024;
        });

        //创建一个线程
        new Thread(futureTask2,"lucy").start();
        new Thread(futureTask1,"luc2").start();
        while (!futureTask2.isDone()){
            System.out.println("wait....");
        }
        //调用Futuretask2的get方法
        System.out.println(futureTask2.get());
        System.out.println(futureTask1.get());
        System.out.println(Thread.currentThread().getName()+" come over");

    }

}
