package com.gzy.seckill.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gzy.seckill.pojo.User;
import com.gzy.seckill.service.IGoodsService;
import com.gzy.seckill.vo.DetailVo;
import com.gzy.seckill.vo.SeckillGoodsVo;
import com.gzy.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @RequestMapping(value = "/toList", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toList(Model model, User user, HttpServletRequest request, HttpServletResponse response) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String html = valueOperations.get("goodsList");
        if (!StringUtils.isEmpty(html)) {
            return html;
        }

        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsService.findGoodsVo());

        WebContext webContext = new WebContext(request, response, request.getServletContext(),
                request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsList", webContext);
        if (!StringUtils.isEmpty(html)) {
            valueOperations.set("goodsList", html, 60, TimeUnit.SECONDS);
        }
        return html;
    }

    @RequestMapping(value = "/detail/{goodsId}", method = RequestMethod.GET)
    @ResponseBody
    public RespBean toDetail(User user, @PathVariable Long goodsId) {
        SeckillGoodsVo seckillGoodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = seckillGoodsVo.getStartDate();
        Date endDate = seckillGoodsVo.getEndDate();
        Date nowDate = new Date();
        int remainSeconds;

        if (nowDate.before(startDate)) {
            // 秒杀还未开始 小于0
            remainSeconds = (int) ((nowDate.getTime() - startDate.getTime()) / 1000);
        } else if (nowDate.after(endDate)) {
            // 秒杀已经结束
            remainSeconds = 0;
        } else {
            // 秒杀进行中 大于0
            remainSeconds = (int) ((endDate.getTime() - nowDate.getTime()) / 1000);
        }

        DetailVo detailVo = new DetailVo();
        detailVo.setUser(user);
        detailVo.setSeckillGoodsVo(seckillGoodsVo);
        detailVo.setRemainSeconds(remainSeconds);
        return RespBean.success(detailVo);
    }
}
