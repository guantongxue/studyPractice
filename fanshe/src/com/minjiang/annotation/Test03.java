package com.minjiang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther guannw
 * @create 2021/10/10 21:43
 */
//自定义注解
public class Test03 {

    //注解可以显示赋值，如果没有默认值，我们就必须给注解赋值
    @MyAnnotation2(name = "guannw",schools = "北京大学",age=18)
    public void test(){

    }
    @MyAnnotation3("guannw")
    public void test2(){

    }
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation2{
    //注解的参数：参数类型+参数名（）
    String name() default "";
    int age() default 0;
    int id() default -1;//如果默认值为-1，代表不存在，indexof 如果找不到就返回-1

    String[] schools() default {"西部开源","清华大学"};
}
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation3{
    String value();
}