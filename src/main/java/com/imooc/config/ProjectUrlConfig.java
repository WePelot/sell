package com.imooc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 项目路径配置
 * @author hongcj
 * @version V1.0
 * @since 2017-08-09 15:02
 */
@Component
@Data
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlConfig {

    /**
     * 微信公众平台授权url
     */
    private String wechatMpAuthorize;

    /**
     * 微信开发平台授权url
     */
    private String wechatOpenAuthorize;

    /**
     * 系统url
     */
    private String sell;
}
