package com.minjiang.sync;

import java.util.concurrent.*;

/**
 * @auther guannw
 * @create 2021/7/29 22:18
 */
public class UseFuture implements Callable<String>  {

    private String param;

    public UseFuture(String param){
        this.param = param;
    }
    @Override
    public String call() throws Exception {
        //模拟执行业务
        TimeUnit.SECONDS.sleep(3);
        return this.param+"任务完成";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String task1 = "task1";
        String task2 = "task2";
        FutureTask<String> futureTask1 = new FutureTask<String>(new UseFuture(task1));
        FutureTask<String> futureTask2 = new FutureTask<String>(new UseFuture(task2));
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(futureTask1);//异步操作
        executorService.submit(futureTask2);//异步操作

        System.out.println("执行中...");

        TimeUnit.SECONDS.sleep(2);//处理其他相关的任务。
        String result1 = futureTask1.get();
        String result2 = futureTask2.get();
        System.out.println("数据处理完成。。" + result1);
        System.out.println("数据处理完成。。" + result2);
    }
}
