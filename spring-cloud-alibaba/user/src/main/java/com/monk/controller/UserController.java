package com.monk.controller;


import com.monk.feign.service.PowerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    public static final String POWER_URL = "http://service-power";

    @Autowired
    private PowerService powerService;

    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }

    @GetMapping("/getPower.do")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public Map<String, Object> getPower() {
        Map<String, Object> result = new HashMap<String, Object>();
        result = restTemplate.getForObject(POWER_URL + "/getPower.do", Map.class);
        return result;
    }

    @GetMapping("/getFeignPower.do")
    public Map<String, Object> getFeignPower() {
        Map<String, Object> result = new HashMap<String, Object>();
        result = powerService.getPower();
        return result;
    }

    private Map<String, Object> fallbackMethod() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", "500");
        result.put("data", "默认的降级方法Controler");
        return result;
    }
}
