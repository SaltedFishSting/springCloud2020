package com.orange.controller;

import com.orange.model.PayMent;
import com.orange.model.Result;
import com.orange.service.MessageProvider;
import com.orange.service.MyMessageController;
import com.orange.service.PayMentFeign;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
public class PayMentController {

    @Autowired
    private PayMentFeign payMentFeign;

    @Autowired
    private MessageProvider messageProvider;

    @Autowired
    private MyMessageController myMessageController;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/cloud/payMent/{id}")
    public Result<PayMent> get(@PathVariable String id) {
        Long t1 = System.currentTimeMillis();
        Result<PayMent> s = payMentFeign.paymentGet(id);
        Long t2 = System.currentTimeMillis();
        System.out.println((t2 - t1) / 1000);
        System.out.println(s);

        Map map = new LinkedHashMap();

        return s;
    }

//    @Value("${config.info}")
//    private String configInfo;
//
//    @GetMapping("/configInfo")
//    public String getConfigInfo() {
//        return configInfo;
//    }


    @GetMapping("/messageProvider/{msg}")
    public String messageProvider(@PathVariable String msg) {
        Map<String,String> map = new HashMap<>();
        map.put(msg, msg);
        messageProvider.send(map);


        return "asd";
    }
    @GetMapping("/messageProvider2/{msg}")
    public Map messageProvider2(@PathVariable String msg) {
        Map<String,String> map = new HashMap<>();
        map.put(msg, msg);
        myMessageController.sendLogMessage(map);
        return map;
    }


    @GetMapping("/rocketmq/{msg}")
    public Object callback(@PathVariable String msg) throws Exception {
        //总共发送五次消息
        // 如下两种方式等价
        rocketMQTemplate.convertAndSend("orange", "orange hello");

        return "成功";
    }

}



