package com.minjiang;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author guannw
 * @date 2021/11/3 下午12:52
 */


//测试ip
public class Test01 {

    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");

            System.out.println(inetAddress);

            InetAddress inetAddress2 = InetAddress.getByName("www.baidu.com");
            System.out.println(inetAddress2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
