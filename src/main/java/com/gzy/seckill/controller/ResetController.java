package com.gzy.seckill.controller;


import com.gzy.seckill.service.IUserService;
import com.gzy.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/reset")
public class ResetController {

    @Autowired
    IUserService userService;

    @GetMapping("start")
    @ResponseBody
    public RespBean createUser() {
        return RespBean.success();
    }
}
