package com.imooc.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 允许前端Cors跨域访问
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-09-08 15:47
 */
@Configuration
public class CorsWebAppConfigurer  extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST")
            .allowCredentials(false).maxAge(3600);
    }
}
