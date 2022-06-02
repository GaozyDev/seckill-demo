package com.gzy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzy.seckill.pojo.Goods;
import com.gzy.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-06-02
 */
public interface IGoodsService extends IService<Goods> {

    List<GoodsVo> findGoodsVo();
}
