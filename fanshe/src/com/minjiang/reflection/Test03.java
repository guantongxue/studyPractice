package com.minjiang.reflection;

/**
 * @auther guannw
 * @create 2021/10/10 22:27
 */
//ʲô�з���
public class Test03 {

    public static void main(String[] args) throws ClassNotFoundException {
        //ͨ�������ȡ���class����
        Class<?> c1 = Class.forName("com.minjiang.reflection.User");
        Class<?> c2 = Class.forName("com.minjiang.reflection.User");
        Class<?> c3 = Class.forName("com.minjiang.reflection.User");
        Class<?> c4 = Class.forName("com.minjiang.reflection.User");
        System.out.println(6+6+"sss"+6+6);

        //һ�������ڴ���ֻ��һ��class����
        //һ���౻���غ� ����������ṹ���ᱻ��װ��Class������
        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());
        System.out.println(c4.hashCode());
    }
}
class User{
    private String name;
    private int id;
    private int age;

    public User() {
    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}