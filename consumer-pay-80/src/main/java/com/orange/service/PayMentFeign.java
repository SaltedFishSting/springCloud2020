package com.orange.service;


import com.orange.model.PayMent;
import com.orange.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",fallback =PayMentFallbackService.class)
public interface PayMentFeign {

    @GetMapping("/payMent/get/{id}")
    public Result<PayMent> paymentGet(@PathVariable("id") String id);

    @GetMapping("/payMent/insert/{serial}")
    public Result<PayMent> paymentInsert(@PathVariable("id") String id);
}
