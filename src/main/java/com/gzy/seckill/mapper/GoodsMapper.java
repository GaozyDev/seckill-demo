package com.gzy.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzy.seckill.pojo.Goods;
import com.gzy.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2022-06-02
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
