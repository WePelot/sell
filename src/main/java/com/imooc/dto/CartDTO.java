package com.imooc.dto;

import lombok.Data;

/**
 * 购物车
 * @author hongcj
 * @version V1.0
 * @since 2017-07-19 14:39
 */
@Data
public class CartDTO {

    /*商品ID*/
    private String productId;

    /*商品数量*/
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CartDTO() {
    }
}
