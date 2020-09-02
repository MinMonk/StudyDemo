package com.monk.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestStr {
    
    private static final Logger logger = LoggerFactory.getLogger(TestStr.class);
    
    public static void main(String[] args) {
        String str = "abc";
        try {
            
            str.substring(5);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("Failed to substring string, str:{}", str, e);
            logger.error("{}", e.getMessage(), e);
        }
    }
    
}