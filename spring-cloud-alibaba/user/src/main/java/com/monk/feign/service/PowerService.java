package com.monk.feign.service;

import com.monk.feign.fallback.PowerFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(name="service-power", fallback = PowerFeignFallBack.class)
public interface PowerService {

    @RequestMapping("/getPower.do")
    public Map<String, Object> getPower();
}
