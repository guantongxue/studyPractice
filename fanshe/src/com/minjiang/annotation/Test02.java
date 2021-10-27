package com.minjiang.annotation;

import java.lang.annotation.*;

/**
 * @auther guannw
 * @create 2021/10/10 21:17
 */
public class Test02 {

    @MyAnnotation
    public void test(){

    }
}

//Target 表示我们的注解可以用在那些地方
//Retention 表示我们可以在哪个什么地方还有效soure<class<runtime
//Documented 是否生成在我们的javadoc 文档中，javadoc就是我们看源码上面的注解
//inherited 表示子类是否继承父类的注解
@Target(value = {ElementType.METHOD,ElementType.TYPE})//设置在方法和类上使用
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface MyAnnotation{

}
