package com.zy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zy.pojo.User;

@Controller
public class ThymeleafController {
	@GetMapping(value = "/test")
    public ModelAndView test(HttpServletRequest req) {
        // UserEntity userEntity = getCurrentUser(req);
        User user = new User();
        user.setName("tom");
        user.setId(234);
        user.setAddress("henan nanyang");
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("/show.html");
        return mv;
    }
}
