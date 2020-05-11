package com.orange.controller;


import com.orange.dao.PayMentDao;
import com.orange.model.PayMent;
import com.orange.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payMent")
public class PayMentController {

    @Autowired
    private PayMentDao payMentDao;


    @GetMapping("/get/{id}")
    public Result<PayMent> get(@PathVariable String id) {
        System.out.println(id+"来了8002");
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){

        }
        Result<PayMent> result = new Result<>();
        result.code = "0000";
        result.msg = "ok";
        result.data = payMentDao.selectById(id);
        return result;
    }

    @GetMapping("/insert/{serial}")
    public Result<PayMent> insert(@PathVariable String serial) {
        Result<PayMent> result = new Result<>();
        PayMent payMent =new PayMent();
        payMent.setId(UUID.randomUUID().toString());
        payMent.setSerial(serial);
        payMentDao.insert(payMent);
        result.code = "0000";
        result.msg = "ok";
        return result;




    }

}
