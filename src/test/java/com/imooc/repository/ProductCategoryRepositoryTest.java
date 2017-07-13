package com.imooc.repository;

import static org.junit.Assert.*;

import com.imooc.dataobject.ProductCategory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-12 15:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOne(){
        ProductCategory one = productCategoryRepository.findOne(1);
        System.out.println(one.toString());
    }
    @Test
    public void saveTest(){
        ProductCategory productCategory = productCategoryRepository.findOne(2);
        productCategory.setCategoryName("男生最爱7");
        productCategoryRepository.save(productCategory);
    }
}