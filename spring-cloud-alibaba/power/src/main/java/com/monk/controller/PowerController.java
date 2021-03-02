package com.monk.controller;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class PowerController {

    @GetMapping("/getPower.do")
    public Map<String, Object> getPower(String name) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", "200");
        result.put("data", "power");
        if(null ==  name){
            // throw new RuntimeException("mock exception");
            // try {
            //     TimeUnit.SECONDS.sleep(10);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
        }
        return result;
    }
}
