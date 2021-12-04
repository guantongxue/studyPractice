package com.minjiang.config;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author guannw
 * @date 2021/11/18 上午11:13
 */

public class tes {
    private static DateFormat clockMinuteFormat = new SimpleDateFormat("HH:mm");

    public static void main(String[] args) throws ParseException {
//        Integer a = null;
//        System.out.println( a != null && a.equals(0))

//        Map<Integer,String> map = new HashMap<>();
//        map.put(1,"W");
//        map.put(2,"S");
//
//        System.out.println(map.size());
//        System.out.println(map.get(1));
//        Date date = new Date();
//        Date now = new Date();
//        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
//        //获取今天的日期
//        String nowDay = sf.format(now);
//
//
//        //对比的时间
//        String day = sf.format(date);
//
//        System.out.println(day.equals(nowDay));

//        Date now = clockMinuteFormat.parse("23:02");
//        Date start = clockMinuteFormat.parse("23:01");
//        Date end = clockMinuteFormat.parse("00:00");
//
//        long nowTime = now.getTime();
//        long startTime = start.getTime();
//        long endTime = end.getTime();
//        System.out.println(nowTime);
//        System.out.println(startTime);
//        System.out.println(endTime);
//        System.out.println(nowTime >= startTime && nowTime <= endTime);
//
//
//        Integer days = (int) ((new Date().getTime() - new Date().getTime()) / (1000 * 3600 * 24));
//        System.out.println(days.equals(0));
//        int awardPoints = 0;
//        String[] scores  = ",2".split(",");
//        for (String score : scores){
//            System.out.println(score);
//            System.out.println(StringUtils.isEmpty(score));
////            awardPoints += Integer.valueOf(score);
//        }

//        DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String test = "2021-11-29 11:03:01";
//
//
//        Date d = null;
//        d = timeFormat.parse(test);
//
//
//        DateFormat cardNoTimeFormat = new SimpleDateFormat("yyyyMMdd");
//        Date now = new Date();
//        //获取今天的日期
//        String nowDay = cardNoTimeFormat.format(now);
//        //对比的时间
//        String day = cardNoTimeFormat.format(d);
//        System.out.println(day.equals(nowDay));

//        Date now = clockMinuteFormat.parse("11:04");
//        Date start = clockMinuteFormat.parse("11:00");
//
//        long nowTime = now.getTime();
//        long startTime = start.getTime();
//        long between =  nowTime - startTime;
//        System.out.println(between);
//        System.out.println(nowTime >= startTime);

//        Calendar time = Calendar.getInstance();
//
//        time.add(Calendar.DATE, -1);
//
//        time.set(Calendar.HOUR_OF_DAY, 23);
//
//        time.set(Calendar.MINUTE, 0);
//
//        time.set(Calendar.SECOND, 0);
//
//        time.set(Calendar.MILLISECOND, 0);
//
//        System.out.println(time.getTime());


        Calendar calendar = Calendar.getInstance();

        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 48);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        System.out.println("一个小时前的时间：" + df.format(calendar.getTime()));
        System.out.println("当前的时间：" + df.format(new Date()));

        System.out.println(calendar.getTime());

    }
}
