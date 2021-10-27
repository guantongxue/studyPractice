package com.minjiang;

import redis.clients.jedis.Jedis;

/**
 * @auther guannw
 * @create 2021/7/4 22:58
 */
public class TestPing {

    public static void main(String[] args) {
        //1.new  Jedis对象即可
        Jedis jedis = new Jedis("192.168.35.128",6379);
        //jedis所有命令就是我们之前学习的所有命令，所以之前的指令学习很重要
        System.out.println(jedis.ping());
    }
}
