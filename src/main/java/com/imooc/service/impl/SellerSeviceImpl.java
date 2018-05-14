package com.imooc.service.impl;

import com.imooc.dataobject.SellerInfo;
import com.imooc.repository.SellerInfoRepository;
import com.imooc.service.SellerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-21 13:24
 */
@Service
public class SellerSeviceImpl implements SellerService{
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
