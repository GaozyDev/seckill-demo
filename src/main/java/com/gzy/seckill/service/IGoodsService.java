package com.gzy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzy.seckill.pojo.Goods;
import com.gzy.seckill.vo.SeckillGoodsVo;

import java.util.List;


public interface IGoodsService extends IService<Goods> {

    List<SeckillGoodsVo> findGoodsVo();

    SeckillGoodsVo findSeckillGoodsVoByGoodsId(Long goodsId);
}
