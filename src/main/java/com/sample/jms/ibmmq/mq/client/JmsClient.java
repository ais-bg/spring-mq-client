package com.sample.jms.ibmmq.mq.client;

public interface JmsClient {

    public void send(String msg, String correlationId, String messageID, String qName, String replyQueue);

    public String receive(String correlationId, String qName);

}
