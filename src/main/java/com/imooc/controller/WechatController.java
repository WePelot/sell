package com.imooc.controller;

import com.imooc.config.ProjectUrlConfig;
import com.imooc.converter.WxMappingJackson2HttpMessageConverter;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-09 10:27
 */
//@RestController
@Controller
@Slf4j
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {

        //以下下是自己有服务号时使用的方法
//        String url = projectUrlConfig.getWechatMpAuthorize() + "/wechat/userInfo";
//        //因为测试号没办法进行支付，这里用的为师兄干货这个测试服务号进行认证
//        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
//        log.info("【微信网页授权】 获取code, result={}",redirectUrl);
//        return "redirect:" + redirectUrl;

        //以下为使用借用的服务号的使用方法
        RestTemplate template = new RestTemplate();
//        template.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        String url = "http://sell.springboot.cn/sell/wechat/authorize?returnUrl=http://sell.springboot.cn/openid.html";
//        String response = template.getForObject(url, String.class);
//        return response;


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");
        headers.add(
                "Accept",
                "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,image/webp,*/*;q=0.8");
        headers.add("Accept-Encoding", "gzip, deflate, sdch");
        headers.add("Cache-Control", "max-age=0");
        headers.add("Connection", "keep-alive");
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<String> responseEntity = template.exchange(url,
                HttpMethod.GET, requestEntity, String.class);
        HttpStatus status = responseEntity.getStatusCode();
        return String.valueOf(status.is2xxSuccessful());
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
        @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }

        String openId = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + openId;
//        return "redirect:" + returnUrl + "?openid=oTgZpwS5cH2oGp4cfGb4rZPF2dbY";
    }
}
