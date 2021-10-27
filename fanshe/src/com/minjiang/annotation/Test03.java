package com.minjiang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther guannw
 * @create 2021/10/10 21:43
 */
//�Զ���ע��
public class Test03 {

    //ע�������ʾ��ֵ�����û��Ĭ��ֵ�����Ǿͱ����ע�⸳ֵ
    @MyAnnotation2(name = "guannw",schools = "������ѧ",age=18)
    public void test(){

    }
    @MyAnnotation3("guannw")
    public void test2(){

    }
}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation2{
    //ע��Ĳ�������������+����������
    String name() default "";
    int age() default 0;
    int id() default -1;//���Ĭ��ֵΪ-1���������ڣ�indexof ����Ҳ����ͷ���-1

    String[] schools() default {"������Դ","�廪��ѧ"};
}
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation3{
    String value();
}