package com.imooc.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by hongcj
 * 2017/7/18 22:35.
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {
    @Id
    private String detailId;

    /*订单id*/
    private String orderId;

    /*商品id*/
    private String productId;

    /*商品名称*/
    private String productName;

    /*商品单价*/
    private BigDecimal productPrice;

    /*商品数量*/
    private Integer productQuantity;

    /*商品小图*/
    private String productIcon;
}
