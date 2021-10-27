package com.minjiang.reflection;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @auther guannw
 * @create 2021/10/22 14:58
 */
public class Test08 {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();//获得系统的类加载器
        System.out.println(systemClassLoader);
        //获得系统类加载器的父类加载器-->扩展类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);

        //获取扩展类加载器的父类加载器-->根类加载器（c、c++）
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        ClassLoader classLoader = Class.forName("com.minjiang.reflection.Test08").getClassLoader();
        System.out.println(classLoader);

        //测试jdk内部的类是谁加载的
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);

        //如何获得系统类加载器可以加载的路径
        System.out.println(System.getProperty("java.class.path"));
    }
}
