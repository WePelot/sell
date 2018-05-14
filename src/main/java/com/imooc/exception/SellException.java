package com.imooc.exception;

import com.imooc.enums.ResultEnum;

import lombok.Data;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-19 14:21
 */
@Data
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
}
