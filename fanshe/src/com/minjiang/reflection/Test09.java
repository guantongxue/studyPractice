package com.minjiang.reflection;

/**
 * @auther guannw
 * @create 2021/10/26 23:58
 */

//获得类的信息
public class Test09 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> c1 = Class.forName("com.minjiang.reflection.User");
        System.out.println(c1.getName());//获得包名+类名
        System.out.println(c1.getSimpleName());//获得类名
    }
}
