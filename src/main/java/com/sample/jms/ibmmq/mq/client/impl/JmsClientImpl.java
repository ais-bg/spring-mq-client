package com.sample.jms.ibmmq.mq.client.impl;

import com.sample.jms.ibmmq.mq.MQGateway;
import com.sample.jms.ibmmq.mq.MQProperties;
import com.sample.jms.ibmmq.mq.client.JmsClient;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JmsClientImpl implements JmsClient {

    @Autowired
    MQGateway mqGateway;

    @Override
    public void send(String msg, String correlationId, String qName, String queueManager, String host, String channel, String user, String password) {

        mqGateway.send(msg, correlationId, qName, queueManager, host, channel, user, password);

    }

    @Override
    public String receive(String correlationId, String qName, String queueManager, String host, String channel, String user, String password) {
    	String message = "";
    	try {
			message = mqGateway.receive(correlationId, qName, queueManager, host, channel, user, password);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return message;
    }

}
