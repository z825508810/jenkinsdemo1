package com.zy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zy.dao.UserDao;
import com.zy.dao.UserMapper;
import com.zy.pojo.User;

@RestController
public class UserController {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisTemplate<String, String> redis;

	@GetMapping("/show")
	public Object showUser() {
		return userDao.findAll();
	}

	@GetMapping("/save")
	public Object insert(User user) {
		userDao.save(user);
		return userDao.findAll();
	}
	@GetMapping("/delete")
	public Object delete(int id) {
		userDao.deleteById(id);
		return userDao.findAll();
	}
	//redis
	@GetMapping("/redis/set")
	public String redisSet(String key,String value) {
		ValueOperations<String, String> redisOps = redis.opsForValue();
		redisOps.set(key, value);
		return "ok";
	}
	@GetMapping("/redis/get")
	public String redisGet(String key) {
		ValueOperations<String, String> redisOps = redis.opsForValue();
		return redisOps.get(key);
	}
	@GetMapping("/redis/setUser")
	public String redisSetUser(Integer id) {
		List list=new ArrayList();
		list.add(id);
	    User user3 = (User)userDao.findAllById(list).get(0);
	    User user = userDao.getOne(id);
	    User user2 = new User();
	    user.setId(6);
	    user.setName("zhangyang");
	    System.out.println(user);
	    String jsonUser = JSON.toJSONString(user);
		ValueOperations<String, String> redisOps = redis.opsForValue();
		redisOps.set(String.valueOf(id), jsonUser);
		return "ok";
	}
	@GetMapping("/redis/getUser")
	public Object redisGetUser(String id) {
		ValueOperations<String, String> redisOps = redis.opsForValue();
		String userStr = redisOps.get(id);
		User u=JSON.toJavaObject(JSON.parseObject(userStr), User.class);
		u.setEmail("888");
		return u;
	}
}
