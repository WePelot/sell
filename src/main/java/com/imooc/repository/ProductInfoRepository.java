package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商品信息dao
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-14 11:17
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{
    /*根据上架状态查询商品*/
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
