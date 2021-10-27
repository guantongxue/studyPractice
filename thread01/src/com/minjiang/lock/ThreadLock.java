package com.minjiang.lock;

import java.util.concurrent.TimeUnit;

/**
 * @auther guannw
 * @create 2021/7/26 22:26
 */

class Phone{
    public static synchronized void sendSMS()throws Exception{
        //停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("-----------sendSMS");
    }

    public synchronized void sendEMAIL()throws Exception{
        System.out.println("-----------sendEMAIL");
    }

    public void getHello()throws Exception{
        System.out.println("-----------getHello");
    }
}

/*
* 8种锁
*
* 1.标准访问，先打印短信还是邮件
*
* 2.停留4秒在短信方法内，先打印短信还是邮件
*
* 3.新增普通的hello方法，先是打短信还是hello
*
* 4.现在有两部手机，先打印短信还是邮件
*
* 5.两个静态同步方法，1部手机，先打印短信还是邮件
*
* 6.两个静态同步方法，2部手机，先打印短信还是邮件
*
* 7.1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
*
* 8.1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
* */
public class ThreadLock {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"aa").start();

        Thread.sleep(100);

        new Thread(()->{
            try {
                phone2.sendEMAIL();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"bb").start();
    }
}
