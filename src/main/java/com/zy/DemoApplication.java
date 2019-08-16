package com.zy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
	@GetMapping("/")
	public String hello() {
		return "hello world";
	}
	@GetMapping("/{phone}/{msg}")
	public String sendMsg(@PathVariable("phone")String phone,@PathVariable("msg")String msg) {
		return MSGSender.sendMsg(phone, msg)+"ok?";
	}
	

}
