package com.minjiang.reflection;

/**
 * @auther guannw
 * @create 2021/10/14 9:52
 */

//������ʲôʱ���ʼ��
public class Test07 {
    static {
        System.out.println("main �౻����");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //1.��������
//        Son son = new Son();

        //2.����Ҳ�������������
//        Class c1 = Class.forName("com.minjiang.reflection.Son");

        //3.�������������÷���
        System.out.println(Son.b);
    }
}


class Father{
    static  int b = 2;
    static {
        System.out.println("���౻����");
    }
}

class Son extends Father{
    static {
        System.out.println("���౻����");
        m = 300;
    }
    static int   m = 100;
    static  final  int M = 1;
}