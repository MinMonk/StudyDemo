package com.monk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class NacosConfigApp {

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext ctx = SpringApplication.run(NacosConfigApp.class, args);
        while (true){
            String userName = ctx.getEnvironment().getProperty("user.name");
            String nickName = ctx.getEnvironment().getProperty("user.nick_name");
            String env = ctx.getEnvironment().getProperty("current.env");
            System.out.println(String.format("env:[%s], userName:[%s], nickName:[%s]", new Object[]{env, userName, nickName}));
            TimeUnit.SECONDS.sleep(3);
        }
    }
}
