package com.minjiang.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @auther guannw
 * @create 2021/8/23 14:35
 */

class MyTask extends RecursiveTask<Integer>{

    //拆分差值不超过10，计算10以内运算
    private static final Integer value = 10;
    private int begin;//拆分开始值
    private int end;//拆分结束值
    private int result;//返回结果

    //创建一个有参构造
    public MyTask(int begin,int end){
        this.begin = begin;
        this.end  = end;
    }

    //拆分和合并的过程
    @Override
    protected Integer compute() {

        //判断两值是否 大于10
        if((end - begin) <= value){
            for (int i = begin ; i < end ; i++){
                result = result + i;
            }
        }else{
            //进行进一步拆分
            //获取中间值
            int middle  = (begin + end ) / 2 ;
            //拆分左边
            MyTask myTask01  =  new MyTask(begin,middle);
            MyTask myTask02  =  new MyTask(middle+1,end);
            myTask01.fork();
            myTask02.fork();
            //合并结果
            result = myTask01.join()+myTask02.join();
        }
       return result;
    }
}


public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建MyTask对象
        MyTask myTask = new MyTask(0, 100);
        //创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask =  forkJoinPool.submit(myTask);
        //获取最终合并的结果
        Integer result = forkJoinTask.get();
        System.out.println(result);
        //关闭池对象
        forkJoinPool.shutdown();
    }
}
