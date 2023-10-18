package com.sample.jms.ibmmq.mq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
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

    @JmsListener (destination = "${ibm.mq.incoming-queue}")
    public void onMessage(TextMessage message) throws JMSException {
        log.info("onMessage");
        log.debug("onMessage - Message: {}", message);

        try {
            messageConsumer.accept(message.getText());
        } catch (JMSException e) {
            log.error("Cannot consume message: {}", message, e);
        }

    }

    public void send(String message, String msgID) {
        log.info("Sending message to IBM Messaging Queue {}", message );
        jmsTemplate.convertAndSend(mqProperties.getIncomingQueue(), message, new MessagePostProcessor() {
            @Override
        	public Message postProcessMessage(Message message) throws JMSException {
        		message.setJMSCorrelationID("ID:" + msgID);
        		log.info("Correlation ID: " + message.getJMSCorrelationID());
        		return message;
        	}
        });
    }

    public String receive(String msgID) throws JMSException {
        //    	Enumeration<?> enumeration; 
        //    	
        //    	jmsTemplate.browse("DEV.QUEUE.1", (session, browser) -> {
        //    		enumeration = browser.getEnumeration();
        //    	});
                return "";
    }


}

