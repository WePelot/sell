package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-19 16:45
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
