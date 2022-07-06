package com.gzy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzy.seckill.pojo.Order;
import com.gzy.seckill.pojo.User;
import com.gzy.seckill.vo.SeckillGoodsVo;
import com.gzy.seckill.vo.OrderDetailVo;


public interface IOrderService extends IService<Order> {

    Order seckill(User user, SeckillGoodsVo goods);

    boolean checkCaptcha(User user, Long goodsId, String captcha);

    String createPath(User user, Long goodsId);

    boolean checkPath(User user, Long goodsId, String path);

    OrderDetailVo detail(Long orderId);
}
