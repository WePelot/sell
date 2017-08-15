/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.service.PayService;
import com.imooc.utils.JsonUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-10 10:10
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        //支付方式."微信公众账号支付"
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        //订单号.
        payRequest.setOrderId(orderDTO.getOrderId());
        //订单金额.
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        //订单名字.
        payRequest.setOrderName(ORDER_NAME);
        //微信openid, 仅微信支付时需要
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        log.info("【微信支付】 request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】 response={}",JsonUtil.toJson(payResponse));
        return payResponse;
    }
}
