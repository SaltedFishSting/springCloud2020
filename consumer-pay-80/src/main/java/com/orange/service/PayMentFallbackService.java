package com.orange.service;


import com.orange.model.PayMent;
import com.orange.model.Result;
import org.springframework.stereotype.Component;

@Component
public class PayMentFallbackService implements PayMentFeign {


    @Override
    public Result<PayMent> paymentGet(String id) {

        Result<PayMent> result=new Result<>();
        result.msg="快速失败1  for8001";
        return result;
    }

    @Override
    public Result<PayMent> paymentInsert(String id) {
        return null;
    }
}
