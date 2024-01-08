package com.sample.jms.ibmmq.mq.client.impl;

import com.sample.jms.ibmmq.mq.MQGateway;
import com.sample.jms.ibmmq.mq.client.JmsClient;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JmsClientImpl implements JmsClient {

    @Autowired
    MQGateway mqGateway;

    @Override
    public void send(String msg, String correlationId, String messageID, String qName, String replyQueue) {

        mqGateway.send(msg, correlationId, messageID, qName, replyQueue);

    }

    @Override
    public String receive(String correlationId, String qName) {
    	String message = "";
    	try {
			message = mqGateway.receive(correlationId, qName);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return message;
    }

}
