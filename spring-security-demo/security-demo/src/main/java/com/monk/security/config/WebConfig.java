package com.monk.security.config;

import com.monk.security.filter.CustomFilter;
import com.monk.security.interceptor.CustomInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

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
