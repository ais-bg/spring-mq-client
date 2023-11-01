package com.sample.jms.ibmmq.mq.client;

public interface JmsClient {

    public void send(String msg, String correlationId, String qName, String queueManager);
    //public String receive();

    public String receive(String correlationId, String qName);
}
