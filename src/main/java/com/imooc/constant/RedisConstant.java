package com.imooc.constant;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-21 15:03
 */
public interface RedisConstant {
    String TOKEN_PREFIX = "token_"; //token前缀

    Integer EXPIRE = 7200; //默认过期时间设置为2小时
}
