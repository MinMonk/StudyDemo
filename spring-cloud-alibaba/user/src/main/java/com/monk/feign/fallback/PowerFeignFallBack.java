package com.monk.feign.fallback;

import com.monk.feign.service.PowerService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PowerFeignFallBack implements PowerService {

    @Override
    public Map<String, Object> getPower() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", "500");
        result.put("data", "默认的降级方法Service");
        return result;
    }
}
