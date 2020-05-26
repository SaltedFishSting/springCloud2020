package com.orange.service;


import com.orange.config.MyProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@EnableBinding(value = {MyProcessor.class})
public class MyMessageController {

    @Autowired
    private MyProcessor myProcessor;

    @GetMapping(value = "sendLogMessage")
    public void sendLogMessage(Map message){
        Message<Map> stringMessage = MessageBuilder.withPayload(message).build();
        myProcessor.logOutput().send(stringMessage);
    }
}
