package com.minjiang.reflection;

/**
 * @auther guannw
 * @create 2021/10/14 9:52
 */

//测试类什么时候初始化
public class Test07 {
    static {
        System.out.println("main 类被加载");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //1.主动引用
//        Son son = new Son();

        //2.反射也会产生主动引用
//        Class c1 = Class.forName("com.minjiang.reflection.Son");

        //3.不会产生类的引用方法
        System.out.println(Son.b);
    }
}


class Father{
    static  int b = 2;
    static {
        System.out.println("父类被加载");
    }
}

class Son extends Father{
    static {
        System.out.println("子类被加载");
        m = 300;
    }
    static int   m = 100;
    static  final  int M = 1;
}