package com.imooc.repository;

import com.imooc.dataobject.SellerInfo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-21 11:17
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String>{
    SellerInfo findByOpenid(String openid);
}
