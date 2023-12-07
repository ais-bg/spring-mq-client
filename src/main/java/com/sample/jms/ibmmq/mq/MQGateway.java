package com.sample.jms.ibmmq.mq;


import lombok.extern.slf4j.Slf4j;

import org.apache.activemq.artemis.jms.client.ActiveMQDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.jms.support.destination.JndiDestinationResolver;

import ch.qos.logback.core.net.QueueFactory;
import ch.qos.logback.core.util.JNDIUtil;

import javax.inject.Named;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.naming.*;

import java.util.function.Consumer;

@Named
@Slf4j
public class MQGateway {

    private JmsTemplate jmsTemplate;
    private MQProperties mqProperties;
    private Consumer<String> messageConsumer;

    @Autowired
    public MQGateway(JmsTemplate jmsTemplate, MQProperties mqProperties, @Named("messageConsumer") Consumer<String> messageConsumer) {
        this.jmsTemplate = jmsTemplate;
        this.mqProperties = mqProperties;
        this.messageConsumer = messageConsumer;
    }

    // @JmsListener (destination = "${ibm.mq.incoming-queue}")
    // public void onMessage(TextMessage message) throws JMSException {
    //     log.info("onMessage");
    //     log.debug("onMessage - Message: {}", message);

    //     try {
    //         messageConsumer.accept(message.getText());
    //     } catch (JMSException e) {
    //         log.error("Cannot consume message: {}", message, e);
    //     }

    // }

    public void send(String message, String correlationId, String qName, String replyQueue) {
        log.info("Sending message to IBM Messaging Queue {}", message);
        jmsTemplate.convertAndSend(qName, message, new MessagePostProcessor() {
        	@Override
        	public Message postProcessMessage(Message message) throws JMSException {
        		message.setJMSCorrelationID("ID:" + correlationId);
                message.setJMSReplyTo(ActiveMQDestination.createQueue(ActiveMQDestination.createQueueAddressFromName(replyQueue)));
                log.info("Correlation ID: " + correlationId);
        		return message;
        	}
        });
    }

    public String receive(String correlationId, String qName) throws JMSException {
    	String selector = "JMSCorrelationID = 'ID:" + correlationId + "'";
    	String message = jmsTemplate.receiveSelectedAndConvert(qName, selector).toString();
    	return message;
    }

}

