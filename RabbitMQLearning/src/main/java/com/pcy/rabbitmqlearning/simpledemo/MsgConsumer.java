package com.pcy.rabbitmqlearning.simpledemo;

import com.pcy.rabbitmqlearning.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 消费者类中通过 @RabbitListener 和 @RabbitHandler 注解将一个方法定义为消息监听的方法
 * 其中 @RabbitListener 可以通过定义bindings={@QueueBinding}
 * ，@QueueBinding可以通过赋值value=@Queue(value = RabbitConfig.FANOUT_QUEUE_NAME, durable = "true")定义出消息队列名
 * ，@QueueBinding也可以通过赋值exchange=@Exchange(value = RabbitConfig.TEST_FANOUT_EXCHANGE, type = "fanout")来定义当前方法所监听的Exchange
 * ，以及类型，类型默认是direct
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: MQStudy
 * @Package: com.pcy.rabbitmqlearning.simpledemo
 * @Description: note
 * @Author: pengcy31624
 * @CreateDate: 2020-07-24 13:47
 * @UpdateUser: pengcy31624
 * @UpdateDate: 2020-07-24 13:47
 * @UpdateRemark: The modified content
 * @Version: 1.0
 *
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/

@Component
@Slf4j
public class MsgConsumer {

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(
                                    value = @Queue(value = RabbitConfig.FANOUT_QUEUE_NAME, durable = "true"),
                                    exchange = @Exchange(value = RabbitConfig.TEST_FANOUT_EXCHANGE, type = "fanout"))
                    })
    @RabbitHandler
    public void processFanoutMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received Fanout message : " + msg);
    }

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(
                                    value = @Queue(value = RabbitConfig.FANOUT_QUEUE_NAME1, durable = "true"),
                                    exchange = @Exchange(value = RabbitConfig.TEST_FANOUT_EXCHANGE, type = "fanout"))
                    })
    @RabbitHandler
    public void processFanout1Msg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received Fanout1 message : " + msg);
    }

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(
                                    value = @Queue(value = RabbitConfig.DIRECT_QUEUE_NAME, durable = "true"),
                                    exchange = @Exchange(value = RabbitConfig.TEST_DIRECT_EXCHANGE),
                                    key = RabbitConfig.DIRECT_ROUTINGKEY)
                    })
    @RabbitHandler
    public void processDirectMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received Direct message : " + msg);
    }

    @RabbitListener(
            bindings =
                    {
                            @QueueBinding(
                                    value = @Queue(value = RabbitConfig.TOPIC_QUEUE_NAME, durable = "true"),
                                    exchange = @Exchange(value = RabbitConfig.TEST_TOPIC_EXCHANGE, type = "topic"),
                                    key = RabbitConfig.TOPIC_ROUTINGKEY)
                    })
    @RabbitHandler
    public void processTopicMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received Topic message : " + msg);
    }

}
