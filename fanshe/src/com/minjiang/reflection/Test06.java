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
        System.out.println("A�ྲ̬������ʼ��");
        m = 30;
    }

    static int m = 10;

    public A() {
        System.out.println("A�๹�췽����ʼ��");
    }
}