package com.minjiang.reflection;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @auther guannw
 * @create 2021/10/22 14:58
 */
public class Test08 {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();//���ϵͳ���������
        System.out.println(systemClassLoader);
        //���ϵͳ��������ĸ��������-->��չ�������
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);

        //��ȡ��չ��������ĸ��������-->�����������c��c++��
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        ClassLoader classLoader = Class.forName("com.minjiang.reflection.Test08").getClassLoader();
        System.out.println(classLoader);

        //����jdk�ڲ�������˭���ص�
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);

        //��λ��ϵͳ����������Լ��ص�·��
        System.out.println(System.getProperty("java.class.path"));
    }
}
