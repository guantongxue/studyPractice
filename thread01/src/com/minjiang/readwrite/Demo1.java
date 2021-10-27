package com.minjiang.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @auther guannw
 * @create 2021/8/11 12:35
 */
public class Demo1 {

    public static void main(String[] args) {
        // 创建可重入读写锁对象
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();//读锁
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();//读锁



        //获取写锁
        writeLock.lock();
        System.out.println(".....getwrite");

        //获取读锁
        readLock.lock();
        System.out.println(".....getread");

        //锁降级  释放写锁
        writeLock.unlock();
        System.out.println(".....relase write");

        // 释放读锁
        readLock.unlock();
        System.out.println(".....relase read");

    }
}
