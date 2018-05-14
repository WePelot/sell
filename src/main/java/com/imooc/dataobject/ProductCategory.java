package com.imooc.dataobject;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

/**
 * 类目
 * @author hongcj
 * @version V1.0
 * @since 2017-07-12 15:21
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory {
    /* 类目ID */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /* 类目名称*/
    private String categoryName;

    /* 类目编号*/
    private Integer categoryType;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public ProductCategory() {
    }
}
