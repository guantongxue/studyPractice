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

//Target ��ʾ���ǵ�ע�����������Щ�ط�
//Retention ��ʾ���ǿ������ĸ�ʲô�ط�����Чsoure<class<runtime
//Documented �Ƿ����������ǵ�javadoc �ĵ��У�javadoc�������ǿ�Դ�������ע��
//inherited ��ʾ�����Ƿ�̳и����ע��
@Target(value = {ElementType.METHOD,ElementType.TYPE})//�����ڷ���������ʹ��
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface MyAnnotation{

}
