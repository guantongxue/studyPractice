package com.minjiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class Redis02SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Redis02SpringbootApplication.class, args);
	}

}
