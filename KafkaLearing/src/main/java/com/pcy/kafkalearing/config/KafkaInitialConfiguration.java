package com.pcy.kafkalearing.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Kafka配置类
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: MQStudy
 * @Package: com.pcy.kafkalearing.config
 * @Description: note
 * @Author: pengcy31624
 * @CreateDate: 2020-07-23 10:50
 * @UpdateUser: pengcy31624
 * @UpdateDate: 2020-07-23 10:50
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/
@Configuration
public class KafkaInitialConfiguration {

    /**
     * 创建一个名为testtopic的Topic并设置分区数为8，分区副本数为2
     *
     * @return
     */
    @Bean
    public NewTopic initialTopic() {
        return new NewTopic("testtopic", 8, (short) 2);
    }

    /**
     * 如果要修改分区数，只需修改配置值重启项目即可
     * 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
     *
     * @return
     */
    @Bean
    public NewTopic updateTopic() {
        return new NewTopic("testtopic", 10, (short) 2);
    }

}
