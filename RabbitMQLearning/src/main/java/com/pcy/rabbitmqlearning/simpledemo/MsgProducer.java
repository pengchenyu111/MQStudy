package com.pcy.rabbitmqlearning.simpledemo;

import com.pcy.rabbitmqlearning.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: MQStudy
 * @Package: com.pcy.rabbitmqlearning.simpledemo
 * @Description: note
 * @Author: pengcy31624
 * @CreateDate: 2020-07-24 13:53
 * @UpdateUser: pengcy31624
 * @UpdateDate: 2020-07-24 13:53
 * @UpdateRemark: The modified content
 * @Version: 1.0
 *
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/

@Component
public class MsgProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send2FanoutTestQueue(String massage) {
        rabbitTemplate.convertAndSend(RabbitConfig.TEST_FANOUT_EXCHANGE, "", massage);
    }

    public void send2DirectTestQueue(String massage) {
        rabbitTemplate.convertAndSend(RabbitConfig.TEST_DIRECT_EXCHANGE, RabbitConfig.DIRECT_ROUTINGKEY, massage);
    }

    public void send2TopicTestAQueue(String massage) {
        rabbitTemplate.convertAndSend(RabbitConfig.TEST_TOPIC_EXCHANGE, "test.aaa", massage);
    }

    public void send2TopicTestBQueue(String massage) {
        rabbitTemplate.convertAndSend(RabbitConfig.TEST_TOPIC_EXCHANGE, "test.bbb", massage);
    }

}
