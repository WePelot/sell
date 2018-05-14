package com.imooc.sharding;
import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.utils.ShardingUtil;

import java.util.Collection;
import java.util.LinkedHashSet;

import lombok.extern.slf4j.Slf4j;

/**
 * 分表策略(单列策略)
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-09-11 09:55
 */
@Slf4j
public final class ModuloTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<String> {

    /**
     *  select * from t_order from t_order where order_id = 11
     *          └── SELECT *  FROM t_order_1 WHERE order_id = 11
     *  select * from t_order from t_order where order_id = 44
     *          └── SELECT *  FROM t_order_0 WHERE order_id = 44
     *
     *   这里的shardingValue即为分库键buyer_openid
     */
    @Override
    public String doEqualSharding(final Collection<String> tableNames, final ShardingValue<String> shardingValue) {
        //ds0为储存末尾数为0,，2,4,6,8的订单详情，ds1寸纯末尾为1,3,5,7,9的订单详情
        Integer shardingValueLast = ShardingUtil.getShardingValueLast(shardingValue);
        if(shardingValueLast == null){
            log.error("获取分片键最后一位数字错误,shardingValue={}",shardingValue);
            throw new SellException(ResultEnum.SHARDING_FALUE);
        }

        //判断是奇数还是偶数
        for (String each : tableNames) {
            if (each.endsWith(shardingValueLast / 2 + "")) {
                return each;
            }
        }

        throw new IllegalArgumentException();
    }

    /**
     *  select * from t_order from t_order where order_id in (11,44)
     *          ├── SELECT *  FROM t_order_0 WHERE order_id IN (11,44)
     *          └── SELECT *  FROM t_order_1 WHERE order_id IN (11,44)
     *  select * from t_order from t_order where order_id in (11,13,15)
     *          └── SELECT *  FROM t_order_1 WHERE order_id IN (11,13,15)
     *  select * from t_order from t_order where order_id in (22,24,26)
     *          └──SELECT *  FROM t_order_0 WHERE order_id IN (22,24,26)
     */
    @Override
    public Collection<String> doInSharding(final Collection<String> tableNames, final ShardingValue<String> shardingValue) {
        //分表，1,3,5,7,9和2,4,6,8,0

        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        for (String value : shardingValue.getValues()) {
            Integer shardingValueLast = ShardingUtil.getShardingValueLast(value);
            if(shardingValueLast == null){
                log.error("获取分片键最后一位数字错误,shardingValue={}",shardingValue);
                throw new SellException(ResultEnum.SHARDING_FALUE);
            }
            for (String tableName : tableNames) {
                if (tableName.endsWith(shardingValueLast/2 + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    /**
     *  select * from t_order from t_order where order_id between 10 and 20
     *          ├── SELECT *  FROM t_order_0 WHERE order_id BETWEEN 10 AND 20
     *          └── SELECT *  FROM t_order_1 WHERE order_id BETWEEN 10 AND 20
     */
    @Override
    public Collection<String> doBetweenSharding(final Collection<String> tableNames, final ShardingValue<String> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(tableNames.size());
//        Range<Long> range = shardingValue.getValueRange();
//        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
//            for (String each : tableNames) {
//                if (each.endsWith(i % 2 + "")) {
//                    result.add(each);
//                }
//            }
//        }
        return result;
    }
}