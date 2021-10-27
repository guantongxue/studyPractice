package com.minjiang.lock;

/**
 * @auther guannw
 * @create 2021/7/26 9:31
 */

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * list集合线程不安全
 */
public class ThreadDemo4 {

    public static void main(String[] args) {

        //Vector解决方案
        // List<String> list = new Vector<>();

        //Collections
        //List<String> list = Collections.synchronizedList(new ArrayList<>());

        //Collections
        //List<String> list = Collections.synchronizedList(new ArrayList<>());

        //CopyOnWriteArrayList
        //List<String> list = new CopyOnWriteArrayList<>();

        //hashMap
        //HashMap<String, String> map = new HashMap<>();

     Map<String, String> map = new ConcurrentHashMap<>();

    //HashSet<String> set = new HashSet<>();

        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }

    }
}
