package com.zy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zy.dao.UserDao;
import com.zy.pojo.User;

@RestController
public class UserController {
	@Autowired
	private UserDao userDao;

	@GetMapping("/show")
	public Object showUser() {
		return userDao.findAll();
	}

	@GetMapping("/save")
	public Object insert(User user) {
		userDao.save(user);
		return userDao.findAll();
	}
}
