/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.imooc.sharding.ModuloDatabaseShardingAlgorithm;
import com.imooc.sharding.ModuloTableShardingAlgorithm;
import com.mysql.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sharding-jdbc分库分表
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-09-11 09:49
 */
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource getDataSource() {
        return buildDataSource();
    }

    private DataSource buildDataSource() {
        //设置分库映射
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        //添加两个数据库ds_0,ds_1到map里
        dataSourceMap.put("ds_0", createDataSource("sell"));
        dataSourceMap.put("ds_1", createDataSource("sell_1"));
        //设置默认db为ds_0，也就是为那些没有配置分库分表策略的指定的默认库
        //如果只有一个库，也就是不需要分库的话，map里只放一个映射就行了，只有一个库时不需要指定默认库，但2个及以上时必须指定默认库，否则那些没有配置策略的表将无法操作数据
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "ds_0");

        //设置分表映射，将t_order_0和t_order_1两个实际的表映射到t_order逻辑表
        //0和1两个表是真实的表，t_order是个虚拟不存在的表，只是供使用。如查询所有数据就是select * from t_order就能查完0和1表的
        List<String> tableList = new ArrayList<>();

        tableList.add("order_detail_0");
        tableList.add("order_detail_1");
        tableList.add("order_detail_2");
        tableList.add("order_detail_3");
        tableList.add("order_detail_4");
        tableList.add("order_detail_5");
        tableList.add("order_detail_6");
        tableList.add("order_detail_7");
        tableList.add("order_detail_8");
        tableList.add("order_detail_9");

        TableRule orderTableRule = TableRule.builder("order_detail")
            .actualTables(tableList)
            .dataSourceRule(dataSourceRule)
            .build();

        //具体分库分表策略，按什么规则来分
        ShardingRule shardingRule = ShardingRule.builder()
            .dataSourceRule(dataSourceRule)
            .tableRules(Arrays.asList(orderTableRule))
            .databaseShardingStrategy(new DatabaseShardingStrategy("order_id", new ModuloDatabaseShardingAlgorithm()))
            .tableShardingStrategy(new TableShardingStrategy("order_id", new ModuloTableShardingAlgorithm())).build();

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);

        return dataSource;
    }

    private static DataSource createDataSource(final String dataSourceName) {
        //使用druid连接数据库
        DruidDataSource result = new DruidDataSource();
        result.setDriverClassName(Driver.class.getName());
//        result.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        result.setUrl(String.format("jdbc:mysql://127.0.0.1/%s" + "?characterEncoding=utf-8&useSSL=false", dataSourceName));
        result.setUsername("root");
        result.setPassword("123456");
        return result;
    }
}
