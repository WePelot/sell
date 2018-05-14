package com.imooc.sharding;
import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.utils.ShardingUtil;

import java.util.Collection;
import java.util.LinkedHashSet;

import lombok.extern.slf4j.Slf4j;

/**
 * 分库策略(单列)
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-09-11 09:54
 */
@Slf4j
public class ModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<String> {

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
        //对order_id进行取余，如果余数为0，则放到ds_0数据库，如果为1则分到ds_1数据库

        Integer shardingValueLast = ShardingUtil.getShardingValueLast(shardingValue);
        if(shardingValueLast == null){
            log.error("获取分片键最后一位数字错误,shardingValue={}",shardingValue);
            throw new SellException(ResultEnum.SHARDING_FALUE);
        }

        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValueLast % 2 + "")) {
                return each;
            }
        }
        log.error("轮巡不到需要分片对应的数据库,shardingValue={}",shardingValue);
        throw new SellException(ResultEnum.SHARDING_FALUE);
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (String value : shardingValue.getValues()) {
            Integer shardingValueLast = ShardingUtil.getShardingValueLast(value);
            if(shardingValueLast == null){
                log.error("获取分片键最后一位数字错误,shardingValue={}",shardingValue);
                throw new SellException(ResultEnum.SHARDING_FALUE);
            }
            for (String tableName : availableTargetNames) {
                if (tableName.endsWith(shardingValueLast % 2 + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
        ShardingValue<String> shardingValue) {
        //这里不使用shardingValue作为分片键

        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
//        Range<String> range = shardingValue.getValueRange();
//        for (String i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
//            for (String each : availableTargetNames) {
//                if (each.endsWith(i % 2 + "")) {
//                    result.add(each);
//                }
//            }
//        }
        return result;
    }

}
