package com.imooc.dataobject;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

/**
 * TODO
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-21 11:15
 */
@Entity
@Data
@DynamicUpdate
public class SellerInfo {
    @Id
    private String id;

    private String username;

    private String password;

    private String openid;
}
