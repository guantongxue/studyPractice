package com.minjiang.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.crypto.KeyGenerator;
import java.lang.reflect.Method;

/**
 * @auther guannw
 * @create 2021/7/8 12:52
 */

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

//    编写我们自己的redisTemplate
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();

        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        objectJackson2JsonRedisSerializer.setObjectMapper(om);
        //String 序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //配置序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //hash的key也采用String的序列化
        template.setHashKeySerializer(stringRedisSerializer);
        //value的序列化采用jackson的序列化方式
        template.setValueSerializer(objectJackson2JsonRedisSerializer);
        //hash的value序列化方式采用jackson
        template.setHashValueSerializer(objectJackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

//    @Bean
//    public KeyGenerator keyGenerator(){
//        return new KeyGenerator(){
//            @Override
//            public Object generate(Object target,Method method,Object... params){
//                StringBuffer sb = new StringBuffer();
//                sb.append(target.getClass().getName());
//                sb.append(method.getName());
//                for (Object obj : params){
//                    sb.append(obj.toString());
//                }
//                return sb.toString();
//            }
//        };
//    }
}

