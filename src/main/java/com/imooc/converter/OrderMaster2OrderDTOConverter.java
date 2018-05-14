package com.imooc.converter;

import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-19 15:30
 */
public class OrderMaster2OrderDTOConverter {
    public static OrderDTO converter(OrderMaster orderMaster){
        OrderDTO orderDTO =  new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e-> converter(e)).collect(Collectors.toList());
    }
}
