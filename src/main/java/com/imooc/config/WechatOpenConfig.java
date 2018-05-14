package com.imooc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-21 13:33
 */
@Component
public class WechatOpenConfig {
    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(WxOpenConfigStorage());
        return wxOpenService;
    }

    @Bean
    public  WxMpConfigStorage WxOpenConfigStorage(){
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        //设置APPID和APPSercert
        wxMpConfigStorage.setAppId(wechatAccountConfig.getOpenAppid());
        wxMpConfigStorage.setSecret(wechatAccountConfig.getOpenAppSecert());
        return wxMpConfigStorage;
    }
}
