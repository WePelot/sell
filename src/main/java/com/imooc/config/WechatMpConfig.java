package com.imooc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * 微信公众账号配置
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-09 10:47
 */
@Component
public class WechatMpConfig {
    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        //设置APPID和APPSercert
        wxMpConfigStorage.setAppId(wechatAccountConfig.getMpAppid());
        wxMpConfigStorage.setSecret(wechatAccountConfig.getMpAppSecert());
        return wxMpConfigStorage;
    }
}
