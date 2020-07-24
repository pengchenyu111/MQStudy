package com.pcy.kafkalearing.atguigudemo;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 实现自定义分区的接口
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: MQStudy
 * @Package: com.pcy.kafkalearing.atguigudemo
 * @Description: note
 * @Author: pengcy31624
 * @CreateDate: 2020-07-23 16:30
 * @UpdateUser: pengcy31624
 * @UpdateDate: 2020-07-23 16:30
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/

public class CustomPartitioner implements Partitioner {

    @Override
    public void configure(Map<String, ?> configs) {
    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 控制分区
        return 0;
    }

    @Override
    public void close() {
    }
}