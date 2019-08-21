package com.zy.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
	@Id
	private Integer id;
	private String name;
	private String password;
	private String email;
	private String address;
}
