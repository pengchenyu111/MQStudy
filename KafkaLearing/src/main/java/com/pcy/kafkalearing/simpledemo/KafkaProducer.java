package com.pcy.kafkalearing.simpledemo;

import com.alibaba.fastjson.JSON;
import com.pcy.kafkalearing.base.BaseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;


/**
 * 简单生产者
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: MQStudy
 * @Package: com.pcy.kafkalearing.simpledemo
 * @Description: note
 * @Author: pengcy31624
 * @CreateDate: 2020-07-23 10:57
 * @UpdateUser: pengcy31624
 * @UpdateDate: 2020-07-23 10:57
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/

@RestController
@RequestMapping("api/mq/kafka")
@CrossOrigin
public class KafkaProducer {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 简单发送消息
     *
     * @param message 消息
     */
    @GetMapping(value = "/normal/{message}")
    public void sendMessage1(@PathVariable("message") String message) {
        kafkaTemplate.send("topic1", message);
    }

    /**
     * +
     * 发送封装好的消息
     *
     * @param message 封装好的消息
     */
    @PostMapping(value = "/beanMessage")
    public void sendMessage2(@RequestBody BaseMessage message) {
        // 目前我还没有找到很好的处理方法
        // 还是使用FastJson来转为字符串
        String messageStr = JSON.toJSONString(message);
        kafkaTemplate.send("topic2", messageStr);
    }


    /**
     * 带回溯的写法1
     *
     * @param callbackMessage
     */
    @GetMapping("/callbackOne/{message}")
    public void sendMessage3(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic1", callbackMessage)
                .addCallback(success -> {
                    // 消息发送到的topic
                    String topic = success.getRecordMetadata().topic();
                    // 消息发送到的分区
                    int partition = success.getRecordMetadata().partition();
                    // 消息在分区内的offset
                    long offset = success.getRecordMetadata().offset();
                    System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
                }, failure -> {
                    System.out.println("发送消息失败:" + failure.getMessage());
                });
    }

    @GetMapping("/callbackTwo/{message}")
    public void sendMessage4(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic1", callbackMessage)
                .addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        System.out.println("发送消息失败：" + ex.getMessage());
                    }

                    @Override
                    public void onSuccess(SendResult<String, Object> result) {
                        System.out.println("发送消息成功："
                                + result.getRecordMetadata().topic() + "-"
                                + result.getRecordMetadata().partition() + "-"
                                + result.getRecordMetadata().offset());
                    }
                });
    }
}
