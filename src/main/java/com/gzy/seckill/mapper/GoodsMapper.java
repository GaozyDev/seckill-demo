package com.gzy.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzy.seckill.pojo.Goods;
import com.gzy.seckill.vo.SeckillGoodsVo;

import java.util.List;


public interface GoodsMapper extends BaseMapper<Goods> {

    List<SeckillGoodsVo> findGoodsVo();

    SeckillGoodsVo findSeckillGoodsVoByGoodsId(Long goodsId);
}
