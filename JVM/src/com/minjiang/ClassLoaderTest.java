package com.minjiang;

/**
 * @auther guannw
 * @create 2021/9/22 1:17
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoaderTest classLoader1 = new ClassLoaderTest();
        ClassLoaderTest classLoader2 = new ClassLoaderTest();
        ClassLoaderTest classLoader3 = new ClassLoaderTest();


        //类模板
        Class<? extends ClassLoaderTest> aClass = classLoader1.getClass();
        //类加载器
        ClassLoader classLoader = aClass.getClassLoader();
        System.out.println(classLoader);//应用程序类加载器
        System.out.println(classLoader.getParent());//扩展类加载器
        System.out.println(classLoader.getParent().getParent());//启动类加载器

    }

}
