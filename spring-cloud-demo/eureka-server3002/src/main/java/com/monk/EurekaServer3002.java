package com.monk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer3002
{
    public static void main( String[] args )
    {
        SpringApplication.run(EurekaServer3002.class, args);
    }
}
