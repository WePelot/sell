package com.imooc.utils;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;

import java.util.Objects;

import org.springframework.util.StringUtils;

/**
 *  sharding时的工具类
 * @author hongcj
 * @version V1.0
 * @since 2017-09-11 14:07
 */
public class ShardingUtil {

    //获取分片键的最后一个数字
    public static Integer getShardingValueLast(ShardingValue<String> shardingValue){
        if(Objects.nonNull(shardingValue)){
            //获取分片键的长度
            int length = shardingValue.getValue().length();
            //获取分片键的最后一位
            return Integer.parseInt(shardingValue.getValue().substring(length -1,length));
        }
        return null;
    }

    //获取分片键的最后一个数字
    public static Integer getShardingValueLast(String shardingValue){
        if(!StringUtils.isEmpty(shardingValue)){
            //获取分片键的长度
            int length = shardingValue.length();
            //获取分片键的最后一位
            return Integer.parseInt(shardingValue.substring(length -1,length));
        }
        return null;
    }
}
