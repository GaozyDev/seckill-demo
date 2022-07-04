package com.gzy.seckill.controller;


import com.gzy.seckill.pojo.User;
import com.gzy.seckill.vo.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("info")
    @ResponseBody
    public RespBean info(User user) {
        return RespBean.success(user);
    }
}
