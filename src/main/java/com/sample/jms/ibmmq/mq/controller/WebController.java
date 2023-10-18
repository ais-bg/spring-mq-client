package com.sample.jms.ibmmq.mq.controller;

import com.sample.jms.ibmmq.mq.client.JmsClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {
    @Autowired
    private JmsClient jmsClient;

    @ApiOperation(value = "To publish message to IBM MQ", nickname = "Spring Integration with IBM-MQ ",
          notes = "Endpoint to send message..."
                    + " as input .")
    @RequestMapping(value = "/produce" , method = RequestMethod.POST)
    @ResponseBody
    public String produce(@RequestParam("msg") String msg, @RequestParam("msgID") String msgID) {

        jmsClient.send(msg, msgID);
        return "Message Sent Successfully from Client WebBrowser";

    }

    @ApiOperation(value = "Receive", nickname = "Read Test", notes = "Read Msg Test")
    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    @ResponseBody
    public String receive(@RequestParam("msgID") String msgID) {
    	return jmsClient.receive(msgID);
    }

}
