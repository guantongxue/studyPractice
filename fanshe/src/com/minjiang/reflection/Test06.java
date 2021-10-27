package com.minjiang.reflection;

/**
 * @auther guannw
 * @create 2021/10/14 8:57
 */
public class Test06 {

    public static void main(String[] args) {
        A a = new A();
        System.out.println(A.m);
    }
}


class A{

    static {
        System.out.println("A类静态代码块初始化");
        m = 30;
    }

    static int m = 10;

    public A() {
        System.out.println("A类构造方法初始化");
    }
}