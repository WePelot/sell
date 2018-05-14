package com.imooc.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 商品详情
 * @author hongcj
 * @version V1.0
 * @since 2017-07-17 11:28
 */
@Data
public class ProductInfoVO implements Serializable{

    private static final long serialVersionUID = -3895834204864685262L;

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
