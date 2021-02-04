package com.monk.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(name="SERVER-POWER")
public interface PowerFeignClient {

    @RequestMapping("/getPower.do")
    public Map<String, Object> getPower();
}
