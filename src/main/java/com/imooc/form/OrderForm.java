package com.imooc.form;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 创建订单时参数
 * @author hongcj
 * @version V1.0
 * @since 2017-07-19 16:22
 */
@Data
public class OrderForm {
    /*姓名*/
    @NotEmpty(message = "姓名不能为空")
    private String name;
    /* 手机号码*/
    @NotEmpty(message = "手机号码不能为空")
    private String phone;
    /*地址*/
    @NotEmpty(message = "地址不能为空")
    private String address;
    /*用户的微信openid*/
    @NotEmpty(message = "微信openId不能为空")
    private String openid;
    //购物车
    @NotEmpty(message = "购物车不能为空")
    private String items;

}
