package com.zy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zy.util.MSGSender;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	//解决hibernate懒加载问题
	@Bean
	public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
		return new OpenEntityManagerInViewFilter();
	}
	@GetMapping("/")
	public String hello() {
		return "hello world";
	}
	@GetMapping("/{phone}/{msg}")
	public String sendMsg(@PathVariable("phone")String phone,@PathVariable("msg")String msg) {
		return MSGSender.sendMsg(phone, msg)+"ok?";
	}
	

}
