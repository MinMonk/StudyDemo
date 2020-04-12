package com.monk.security.config;

import com.monk.security.propertites.SecurityProperties;
import com.monk.security.service.SmsCodeSender;
import com.monk.security.validate.imagecode.ImageCodeGenerator;
import com.monk.security.validate.CodeGenerator;
import com.monk.security.validate.smscode.DefaultSmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SecurityCoreConfiguration
 * @Description: TODO
 * @Author Monk
 * @Date 2020/4/5
 * @Version V1.0
 **/
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfiguration {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public CodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
