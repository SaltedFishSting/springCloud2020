package com.orange.controller;

import com.orange.model.PayMent;
import com.orange.model.Result;
import com.orange.service.PayMentFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class PayMentController {

    @Autowired
    private PayMentFeign payMentFeign;

    @GetMapping("/cloud/payMent/{id}")
    public Result<PayMent> get(@PathVariable String id){
        Long t1=System.currentTimeMillis();
        Result<PayMent> s=payMentFeign.paymentGet(id);
        Long t2=System.currentTimeMillis();
        System.out.println((t2-t1)/1000);
        System.out.println(s);

        Map map=new LinkedHashMap();

        return  s;
    }

}
