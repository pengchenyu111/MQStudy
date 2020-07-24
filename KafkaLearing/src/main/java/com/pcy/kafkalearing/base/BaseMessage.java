package com.pcy.kafkalearing.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;

/**
 * 封装消息对象
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: MQStudy
 * @Package: com.pcy.kafkalearing.base
 * @Description: note
 * @Author: pengcy31624
 * @CreateDate: 2020-07-23 11:32
 * @UpdateUser: pengcy31624
 * @UpdateDate: 2020-07-23 11:32
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseMessage {
    private String content;
    private String sendDate;
}
