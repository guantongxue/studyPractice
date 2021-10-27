package com.minjiang.reflection;

/**
 * @auther guannw
 * @create 2021/10/11 20:50
 */
//����Class��Ĵ�����ʽ����Щ
public class Test04 {

    public static void main(String[] args) throws ClassNotFoundException {
        String s= "ssss";
        s = s+1;
        Person person = new Student();
        System.out.println("������ǣ�"+person.name);
        //��ʽһ��ͨ��������
        Class c1 = person.getClass();
        System.out.println(c1.hashCode());

        //��ʽ����forname���
        Class c2 = Class.forName("com.minjiang.reflection.Student");
        System.out.println(c2.hashCode());

        //��ʽ����ͨ���������
        Class c3 = Student.class;
        System.out.println(c3.hashCode());

        //��ʽ�ģ������������͵İ�װ����һ��Type����
        Class<Integer> c4 = Integer.TYPE;
        System.out.println(c4);

        //��ø�������
        Class c5 = c1.getSuperclass();
        System.out.println(c5);
    }
}

class Person{
    public String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
class Student extends Person{
    public Student() {
        this.name = "ѧ��";
    }
}

class Teacher extends Person{
    public Teacher() {
        this.name = "��ʦ";
    }
}