package com.minjiang.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @auther guannw
 * @create 2021/8/8 22:34
 */

//资源类
class MyCache{

    //创建map集合
    private volatile Map<String , Object> map = new HashMap<>();

    //创建读写锁的对象
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //放数据
    public void put(String key , Object value) throws InterruptedException {
        //添加写锁
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" 写操作"+key );
            //暂停一会
            TimeUnit.MILLISECONDS.sleep(300);

            //放数据
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+" 写完了"+key );

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            //释放写锁
            readWriteLock.writeLock().unlock();
        }

    }

    //取数据
    public Object get(String key) throws InterruptedException {
        readWriteLock.readLock().lock();
        try {
            Object result = null;
            System.out.println(Thread.currentThread().getName()+"正在读取操作"+key);
            TimeUnit.MILLISECONDS.sleep(300);

            result = map.get(key);
            System.out.println(Thread.currentThread().getName()+" 取完数据了"+key );
            return result;
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();

        }
        return null;
    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        //创建线程放数据
        for (int i = 0 ; i< 5 ; i++){
            final  int num = i;
            new Thread(()->{
                try {
                    myCache.put(num+"",num+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
        //创建线程取数据
        for (int i = 0 ; i< 5 ; i++){
            final  int num = i;
            new Thread(()->{
                try {
                    myCache.get(num+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}
