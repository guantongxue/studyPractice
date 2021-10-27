package com.minjiang;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minjiang.pojo.User;
import com.minjiang.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther guannw
 * @create 2021/7/8 11:50
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test01(){

//        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
//        redisConnection.flushDb();
//        redisConnection.flushAll();
        redisTemplate.opsForValue().set("mykey","世界您好");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

    @Test
    public void test02() throws JsonProcessingException {

        //真实开发一般传递的都是json,因此都需要对对象进行序列化操作
        User user = new User("测试1",3);

        String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",user);

        System.out.println(redisTemplate.opsForValue().get("user"));
    }

    @Test
    public void test03(){
        redisUtil.set("name","guannaiwei");
        System.out.println(redisUtil.get("name"));
    }


}
