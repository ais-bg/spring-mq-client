package com.sample.jms.ibmmq.mq.client;

public interface JmsClient {

    public void send(String msg, String correlationId, String qName);
    //public String receive();

    public String receive(String correlationId, String qName);
}
