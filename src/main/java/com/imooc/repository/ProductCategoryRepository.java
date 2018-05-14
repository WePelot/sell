package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品类目的dao
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-12 15:31
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
