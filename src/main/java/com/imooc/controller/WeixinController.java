/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * 微信授权
 * @author hongcj
 * @version V1.0
 * @since 2017-08-08 19:11
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    public final String APPID = "wxfc26418b74dcb0a8";
    public final String APPSECRET = "fadb448b94aa080266315a3607b689ea";

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法");
        log.info("code={}",code);
        //获取code后调用获取token接口，获取token，在获取token接口的返回集里有我们需要的openId
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret=" + APPSECRET
            + "&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
    }
}

