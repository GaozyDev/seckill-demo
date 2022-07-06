package com.gzy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzy.seckill.pojo.SeckillOrder;
import com.gzy.seckill.pojo.User;


public interface ISeckillOrderService extends IService<SeckillOrder> {

    Long getResult(User user, Long goodsId);
}
