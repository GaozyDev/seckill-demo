package com.gzy.seckill.vo;

import com.gzy.seckill.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailVo {

    private User user;

    private SeckillGoodsVo seckillGoodsVo;

    private int remainSeconds;
}
