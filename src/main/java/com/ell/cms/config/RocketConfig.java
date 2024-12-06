package com.ell.cms.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketConfig {

    @Value("${rocketmq.name-server}")
    private String nameServer;

    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    @Value("${rocketmq.producer.send-message-timeout}")
    private Integer sendMsgTimeout;

    @Value("${rocketmq.producer.max-message-size}")
    private Integer maxMessageSize;

    @Value("${rocketmq.producer.retry-times-when-send-failed}")
    private Integer retryTimesWhenSendFailed;

    @Value("${rocketmq.producer.retry-times-when-send-async-failed}")
    private Integer retryTimesWhenSendAsyncFailed;

    @Bean
    public RocketMQTemplate rocketMQTemplate() {
        RocketMQTemplate template = new RocketMQTemplate();
        template.setProducer(defaultMqProducer());
        return template;
    }

    @Bean
    public DefaultMQProducer defaultMqProducer() {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr(this.nameServer);
        producer.setProducerGroup(this.producerGroup);
        producer.setSendMsgTimeout(this.sendMsgTimeout);
        producer.setMaxMessageSize(this.maxMessageSize);
        producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        producer.setRetryTimesWhenSendAsyncFailed(this.retryTimesWhenSendAsyncFailed);
        return producer;
    }
}