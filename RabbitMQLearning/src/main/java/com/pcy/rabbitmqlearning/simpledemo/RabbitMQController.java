package com.pcy.rabbitmqlearning.simpledemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: MQStudy
 * @Package: com.pcy.rabbitmqlearning.simpledemo
 * @Description: note
 * @Author: pengcy31624
 * @CreateDate: 2020-07-24 13:58
 * @UpdateUser: pengcy31624
 * @UpdateDate: 2020-07-24 13:58
 * @UpdateRemark: The modified content
 * @Version: 1.0
 *
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/

@RequestMapping("api/mq/rabbitmq")
@RestController
@Slf4j
public class RabbitMQController {

    @Autowired
    private MsgProducer msgProducer;

    @GetMapping(value = "/sendFanout")
    @Transactional(rollbackFor = Exception.class)
    public void sendMsg() {
        msgProducer.send2FanoutTestQueue("this is a test fanout message!");
    }

    @GetMapping(value = "/sendDirect")
    @Transactional(rollbackFor = Exception.class)
    public void sendDirectMsg() {
        msgProducer.send2DirectTestQueue("this is a test direct message!");
    }

    @GetMapping(value = "/sendDirectA")
    @Transactional(rollbackFor = Exception.class)
    public void sendTopicAMsg() {
        msgProducer.send2TopicTestAQueue("this is a test topic aaa message!");
    }

    @GetMapping(value = "/sendTopicB")
    @Transactional(rollbackFor = Exception.class)
    public void sendTopicBMsg() {
        msgProducer.send2TopicTestBQueue("this is a test topic bbb message!");
    }
}
