/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.controller;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.ProductInfoServcie;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * 卖家端商品操作
 * @author hongcj
 * @version V1.0
 * @since 2017-08-18 10:51
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductInfoServcie productInfoServcie;
    /**
     * 商品详情
     * @param page
     * @param size
     * @param map
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
        @RequestParam(value = "size",defaultValue = "10") Integer size,
        Map<String,Object> map){
        PageRequest pageRequest = new PageRequest(page - 1 ,size);
        Page<ProductInfo> productInfoPage = productInfoServcie.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    @RequestMapping("/on_sale")
    public ModelAndView OnSale(@RequestParam String productId,
        Map<String,Object> map){
        try{
             productInfoServcie.onSale(productId);
        }catch (SellException e){
            log.error("【卖家端商品上架】 发生异常{}",e);
            map.put("errorMsg", e.getMessage());
            //跳转到列表页
            map.put("redirectUrl","/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        //跳转到列表页
        map.put("redirectUrl","/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @RequestMapping("/off_sale")
    public ModelAndView OffSale(@RequestParam String productId,
        Map<String,Object> map){
        try{
            productInfoServcie.OffSale(productId);
        }catch (SellException e){
            log.error("【卖家端商品下架】 发生异常{}",e);
            map.put("errorMsg", e.getMessage());
            //跳转到列表页
            map.put("redirectUrl","/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        //跳转到列表页
        map.put("redirectUrl","/seller/product/list");
        return new ModelAndView("common/success", map);
    }



}
