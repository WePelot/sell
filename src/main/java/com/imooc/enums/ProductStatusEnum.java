package com.imooc.enums;

import lombok.Getter;

/**
 * 商品上下架状态
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-17 10:06
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(0,"上架"),
    DOWN(1,"下架")
    ;

    private Integer code;

    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
