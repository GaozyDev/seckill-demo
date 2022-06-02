package com.gzy.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzy.seckill.exception.GlobalException;
import com.gzy.seckill.mapper.UserMapper;
import com.gzy.seckill.pojo.User;
import com.gzy.seckill.service.IUserService;
import com.gzy.seckill.utils.CookieUtil;
import com.gzy.seckill.utils.MD5Util;
import com.gzy.seckill.utils.UUIDUtil;
import com.gzy.seckill.vo.LoginVo;
import com.gzy.seckill.vo.RespBean;
import com.gzy.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2022-06-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        User user = userMapper.selectById(mobile);
        if (null == user) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        if (!MD5Util.fromPassToDBPass(password, user.getSlat()).equals(user.getPassword())) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        String ticket = UUIDUtil.uuid();
        redisTemplate.opsForValue().set("user:" + ticket, user);
        CookieUtil.setCookie(request, response, "userTicket", ticket);
        return RespBean.success();
    }

    @Override
    public User getUserByCookie(String ticket, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(ticket)) {
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + ticket);
        if (user != null) {
            CookieUtil.setCookie(request, response, "userTicket", ticket);
        }
        return user;
    }
}
