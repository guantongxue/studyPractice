package com.minjiang.oom;

import java.util.ArrayList;

/**
 * @auther guannw
 * @create 2021/9/29 23:58
 */
public class Demo03 {
    byte [] aa = new byte[1*1024*1024];//1M

    public static void main(String[] args) {
        ArrayList<Demo03> objects = new ArrayList<>();
        int count = 0;
        try{
            while (true){
                objects.add(new Demo03());
                count += 1;
            }
        }catch (Exception e){
            System.out.println("count:"+count);
            e.printStackTrace();
        }

    }
}
