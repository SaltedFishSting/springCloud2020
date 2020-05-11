package com.orange.service;


import org.springframework.stereotype.Component;

@Component
public class PayMentFallbackService implements PayMentFeign {


    @Override
    public String paymentGet(Integer id) {
        return "快速失败1";
    }

    @Override
    public String paymentInsert(Integer id) {
        return "快速失败2";
    }
}
