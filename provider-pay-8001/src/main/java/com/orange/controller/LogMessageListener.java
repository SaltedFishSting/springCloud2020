package com.orange.controller;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class LogMessageListener {


    /**
     * 通过 MyProcessor.MESSAGE_INPUT 接收消息
     * 然后通过 SendTo 将处理后的消息发送到 MyProcessor.LOG_FORMAT_OUTPUT
     * @param message
     * @return
     */
    @StreamListener(MyProcessor.MESSAGE_INPUT)
    @SendTo(MyProcessor.LOG_FORMAT_OUTPUT)
    public Map processLogMessage(Map message) {
        System.out.println(message);
        message.put("s","s");
        return message;
    }

    /**
     * 接收来自 MyProcessor.LOG_FORMAT_INPUT 的消息
     * 也就是加工后的消息，也就是通过上面的 SendTo 发送来的
     * 因为 MyProcessor.LOG_FORMAT_OUTPUT 和 MyProcessor.LOG_FORMAT_INPUT 是指向同一 exchange
     * @param message
     */
    @StreamListener(MyProcessor.LOG_FORMAT_INPUT)
    public void processFormatLogMessage(Map message) {
        System.out.println(message);

    }

}
