package com.gzy.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {

    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务器异常"),


    LOGIN_ERROR(500210, "用户名或密码不正确"),
    MOBILE_ERROR(500211, "手机号码格式不正确"),
    BIND_ERROR(500212, "参数校验异常"),
    SESSION_ERROR(500215, "用户SESSION不存在"),


    EMPTY_STOCK(500500, "库存不足"),
    REPEAT_ERROR(500500, "该商品每人限购一件"),
    REQUEST_ILLEGAL(500502, "请求非法，请重新尝试"),
    ERROR_CAPTCHA(500503, "验证码错误，请重新输入"),

    ORDER_NOT_EXIST(500300, "订单不存在");

    private final Integer code;
    private final String message;
}
