package com.minjiang.reflection;

import java.lang.annotation.ElementType;

/**
 * @auther guannw
 * @create 2021/10/11 23:49
 */
//�������͵�class
public class Test05 {

    public static void main(String[] args) {
        Class c1 = Object.class;
        Class c2 = Comparable.class;//�ӿ�
        Class c3 = String[].class;//����
        Class c4 = int[][].class;//��ά����
        Class c5 = Override.class;//ע��
        Class c6 = ElementType.class;//ö������
        Class c7 = Integer.class;//����
        Class c8 = void.class; //void����
        Class c9 = Class.class; //class

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);

    }
}
