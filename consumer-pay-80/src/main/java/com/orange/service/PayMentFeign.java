package com.orange.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",fallback =PayMentFallbackService.class )
public interface PayMentFeign {

    @GetMapping("/payMent/get/{id}")
    public String paymentGet(@PathVariable("id") Integer id);

    @GetMapping("/payMent/insert/{serial}")
    public String paymentInsert(@PathVariable("id") Integer id);
}
