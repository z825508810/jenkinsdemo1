package com.zy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zy.pojo.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
}
