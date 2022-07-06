package com.gzy.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzy.seckill.mapper.GoodsMapper;
import com.gzy.seckill.pojo.Goods;
import com.gzy.seckill.service.IGoodsService;
import com.gzy.seckill.vo.SeckillGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<SeckillGoodsVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }

    @Override
    public SeckillGoodsVo findGoodsVoByGoodsId(Long goodsId) {
        return goodsMapper.findSeckillGoodsVoByGoodsId(goodsId);
    }
}
