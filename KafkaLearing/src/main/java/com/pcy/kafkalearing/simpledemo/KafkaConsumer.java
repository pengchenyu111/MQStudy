package com.pcy.kafkalearing.simpledemo;

import com.alibaba.fastjson.JSON;
import com.pcy.kafkalearing.base.BaseMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 简单消费者
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: MQStudy
 * @Package: com.pcy.kafkalearing.simpledemo
 * @Description: note
 * @Author: pengcy31624
 * @CreateDate: 2020-07-23 11:05
 * @UpdateUser: pengcy31624
 * @UpdateDate: 2020-07-23 11:05
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/

@Component
public class KafkaConsumer {

    /**
     * 消费监听
     *
     * @param record
     */
    @KafkaListener(topics = {"topic1"})
    public void onMessage1(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    /**
     * 消费实体消息
     *
     * @param record
     */
    @KafkaListener(topics = {"topic2"})
    public void onMessage2(ConsumerRecord<?, ?> record) {
        BaseMessage message = JSON.parseObject(record.value().toString(), BaseMessage.class);
        System.out.println("消费实体信息：" + message.toString());
    }

    /**
     * 指定topic、partition、offset消费
     *
     * @param record
     * @return void
     * @Description 监听topic1的0号分区，同时监听topic2的0号分区和topic2的1号分区里面offset从1开始的消息
     */
    @KafkaListener(id = "consumer1", groupId = "felix-group", topicPartitions = {
            @TopicPartition(topic = "topic1", partitions = {"0"}),
            /*@TopicPartition(topic = "topic2", partitions = "0", partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "1"))*/
    })
    public void onMessage3(ConsumerRecord<?, ?> record) {
        System.out.println("topic:" + record.topic() + "|partition:" + record.partition() + "|offset:" + record.offset() + "|value:" + record.value());
    }

    /*    */
    /**
     * 批量消费
     * 注意在配置文件中开启批量消费
     *
     * @param records
     *//*
    @KafkaListener(id = "consumer2", groupId = "felix-group", topics = "topic1")
    public void onMessage4(List<ConsumerRecord<?, ?>> records) {
        System.out.println(">>>批量消费一次，records.size()=" + records.size());
        for (ConsumerRecord<?, ?> record : records) {
            System.out.println(record.value());
        }
    }*/



    @Autowired
    ConsumerFactory consumerFactory;

    /**
     * 消息过滤器
     *
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory filterContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        // 被过滤的消息将被丢弃
        factory.setAckDiscarded(true);
        // 消息过滤策略
        factory.setRecordFilterStrategy(consumerRecord -> {
            if (Integer.parseInt(consumerRecord.value().toString()) % 2 == 0) {
                return false;
            }
            //返回true消息则被过滤
            return true;
        });
        return factory;
    }

    /**
     * 消息过滤监听
     *
     * @param record
     */
    @KafkaListener(topics = {"topic1"}, containerFactory = "filterContainerFactory")
    public void onMessage6(ConsumerRecord<?, ?> record) {
        System.out.println(record.value());

    }
}
