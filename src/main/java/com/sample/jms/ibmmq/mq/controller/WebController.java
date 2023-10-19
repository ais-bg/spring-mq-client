package com.sample.jms.ibmmq.mq.controller;

import com.sample.jms.ibmmq.mq.client.JmsClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {
    @Autowired
    private JmsClient jmsClient;

    @ApiOperation(value = "To publish message to IBM MQ", nickname = "Sends a message to a specified queue.",
          notes = "Sends a UTF-8 encoded text message to an IBM MQ queue.")
    @RequestMapping(value = "/produce" , method = RequestMethod.POST, produces="MediaType.TEXT_PLAIN")
    @ResponseBody
    public String produce(@RequestParam("msg") String msg, @RequestParam("correlationId") String correlationId, @RequestParam("qName") String qName) {
        jmsClient.send(msg, correlationId, qName);
        return "Message Sent Successfully from Client WebBrowser";
    }

    @ApiOperation(value = "Receive", nickname = "Browses the next message from a specified queue.", notes = "Retrieves the next available text message from an IBM MQ queue.")
    @RequestMapping(value = "/receive", method = RequestMethod.GET, produces="MediaType.TEXT_PLAIN")
    @ResponseBody
    public String receive(@RequestParam("correlationId") String correlationId, @RequestParam("qName") String qName) {
    	return jmsClient.receive(correlationId, qName);
    }

}
