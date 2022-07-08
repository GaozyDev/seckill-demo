package com.gzy.seckill.controller;


import com.gzy.seckill.pojo.User;
import com.gzy.seckill.service.IUserService;
import com.gzy.seckill.utils.UUIDUtil;
import com.gzy.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("info")
    @ResponseBody
    public RespBean info(User user) {
        return RespBean.success(user);
    }

    @GetMapping("createUser/{count}")
    @ResponseBody
    public RespBean createUser(@PathVariable int count) throws IOException {
        List<User> users = new ArrayList<>(count);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setId(10000000000L + i);
            user.setPassword("b7797cce01b4b131b433b6acf4add449"); // 123456
            user.setNickname(i + "");
            user.setSlat("1a2b3c4d");
            users.add(user);
            String ticket = UUIDUtil.uuid();
            redisTemplate.opsForValue().set("user:" + ticket, user, 30, TimeUnit.DAYS);
            stringBuilder.append(ticket).append("\n");
        }
        userService.saveOrUpdateBatch(users);
        BufferedWriter out = new BufferedWriter(new FileWriter("ticket"));
        out.write(stringBuilder.toString());
        out.close();
        return RespBean.success();
    }

    @GetMapping("deleteUser/{count}")
    @ResponseBody
    public RespBean deleteUser(@PathVariable int count) {
        List<Long> ids = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            ids.add(10000000000L + i);
        }
        userService.removeBatchByIds(ids);
        return RespBean.success();
    }
}
