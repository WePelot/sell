package com.imooc.service;

import com.imooc.dataobject.SellerInfo;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-21 13:22
 */
public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
