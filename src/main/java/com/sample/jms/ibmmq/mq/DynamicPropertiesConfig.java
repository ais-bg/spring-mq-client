package com.sample.jms.ibmmq.mq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class DynamicPropertiesConfig {
    @Value("${ibm.mq.queue.manager}")
    private String queueManager;

    public String getQueueManager() {
        return queueManager;
    }
}
