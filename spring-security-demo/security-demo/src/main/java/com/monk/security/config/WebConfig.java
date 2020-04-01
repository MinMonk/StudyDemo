package com.monk.security.config;

import com.monk.security.filter.CustomFilter;
import com.monk.security.interceptor.CustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class WebConfig implements WebMvcConfigurer {

    /*// 使用autowired自动注入也可以
    @Autowired
    private CustomFilter customFilter;*/

//    @Autowired
//    private CustomInterceptor customInterceptor;

    @Bean
    public FilterRegistrationBean registrationFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        CustomFilter customFilter = new CustomFilter();
        filterRegistrationBean.setFilter(customFilter);

        List<String> filterUrls = new ArrayList<String>();
        filterUrls.add("/user/*");
        filterRegistrationBean.setUrlPatterns(filterUrls);

        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        CustomInterceptor customInterceptor = new CustomInterceptor();
        registry.addInterceptor(customInterceptor);
    }
}
