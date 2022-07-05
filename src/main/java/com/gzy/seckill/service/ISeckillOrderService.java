package com.gzy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzy.seckill.pojo.SeckillOrder;
import com.gzy.seckill.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-06-02
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {

    Long getResult(User user, Long goodsId);
}
