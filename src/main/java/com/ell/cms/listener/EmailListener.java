package com.ell.cms.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "email-topic", consumerGroup = "task-group")
public class EmailListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("接收到的消息: " + message);
    }
}