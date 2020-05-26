package com.orange.service;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.Map;

@EnableBinding(Source.class) //定义消息的推送管道
public class MessageProvider
{
    @Resource
    private MessageChannel output; // 消息发送管道

    public Map send(Map map)
    {

        output.send(MessageBuilder.withPayload(map).build());
        System.out.println("*****serial: "+map);
        return map;
    }
}