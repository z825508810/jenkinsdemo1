package com.zy.test;

import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;

import com.zy.pojo.User;

public class LomboxTest {
public static void main(String[] args) {
	User user = new User();
	user.setName("zzz");
	RedisTemplate d=null;
	RedisAutoConfiguration r=null;
	System.out.println(user);
}
}
