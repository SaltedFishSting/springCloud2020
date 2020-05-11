package com.orange.controller;

import com.orange.model.Result;
import com.orange.service.PayMentFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayMentController {

    @Autowired
    private PayMentFeign payMentFeign;

    @GetMapping("/cloud/payMent")
    public String get(){
        Long t1=System.currentTimeMillis();
        String s=payMentFeign.paymentGet(11);
        Long t2=System.currentTimeMillis();
        System.out.println((t2-t1)/1000);
        System.out.println(s);
        return  s;
    }

}
