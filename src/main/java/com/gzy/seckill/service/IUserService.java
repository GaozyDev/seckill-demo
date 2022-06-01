package com.gzy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzy.seckill.pojo.User;
import com.gzy.seckill.vo.LoginVo;
import com.gzy.seckill.vo.RespBean;

import javax.validation.Valid;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-06-01
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo);
}
