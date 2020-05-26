package com.orange.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.orange.dao.PayMentDao;
import com.orange.model.PayMent;
import com.orange.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payMent")
@RefreshScope
public class PayMentController {

    @Autowired
    private PayMentDao payMentDao;

    //    @HystrixCommand(fallbackMethod = "fallback",commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
//    })
//    @HystrixCommand()
    @GetMapping("/get/{id}")
    public Result<PayMent> get(@PathVariable String id) {
        System.out.println(id + "来了8001");

        if (id.equals("00")) {
            throw new RuntimeException("00啊啊");
        }
        Result<PayMent> result = new Result<>();
        result.code = "0000";
        result.msg = "ok 来自8001";
        result.data = payMentDao.selectById(id);
        return result;
    }

    public Result<PayMent> fallback(@PathVariable("id") String id) {
        Result<PayMent> result = new Result<>();
        result.msg = "快速失败3";
        return result;
    }


    @GetMapping("/insert/{serial}")
    public Result<PayMent> insert(@PathVariable String serial) {
        Result<PayMent> result = new Result<>();
        PayMent payMent = new PayMent();
        payMent.setId(UUID.randomUUID().toString());
        payMent.setSerial(serial);
        payMentDao.insert(payMent);
        result.code = "0000";
        result.msg = "ok";
        return result;
    }



}
