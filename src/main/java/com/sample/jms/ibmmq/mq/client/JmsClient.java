package com.sample.jms.ibmmq.mq.client;

public interface JmsClient {

    public void send(String msg, String msgID);
    //public String receive();

    public String receive(String msgID);
}
