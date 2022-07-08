package com.gzy.seckill.rabbitmq;

import com.gzy.seckill.pojo.SeckillOrder;
import com.gzy.seckill.pojo.User;
import com.gzy.seckill.service.IGoodsService;
import com.gzy.seckill.service.IOrderService;
import com.gzy.seckill.utils.JsonUtil;
import com.gzy.seckill.vo.SeckillGoodsVo;
import com.gzy.seckill.vo.SeckillMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQReceiver {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private IOrderService orderService;

    @RabbitListener(queues = "seckillQueue")
    public void receiver(String message) {
        log.info("接收消息:" + message);
        SeckillMessage seckillMessage = JsonUtil.jsonStr2Object(message, SeckillMessage.class);
        if (seckillMessage == null) {
            return;
        }
        Long goodsId = seckillMessage.getGoodsId();
        User user = seckillMessage.getUser();

        // 检查数据库库存
        SeckillGoodsVo seckillGoodsVo = goodsService.findSeckillGoodsVoByGoodsId(goodsId);
        if (seckillGoodsVo.getStockCount() < 1) {
            return;
        }

        // 检查重复抢购
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        if (seckillOrder != null) {
            return;
        }
        orderService.seckill(user, seckillGoodsVo);
    }
}
