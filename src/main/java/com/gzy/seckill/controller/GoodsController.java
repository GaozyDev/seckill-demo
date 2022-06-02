package com.gzy.seckill.controller;

import com.gzy.seckill.pojo.User;
import com.gzy.seckill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/toList")
    public String toList(Model model, User user) {
        if (null == user) {
            return "login";
        }
        model.addAttribute("user", user);

        System.out.println(user);
        return "goodsList";
    }
}
