package com.monk.security.config;

import com.monk.security.authentication.mobile.SmsCodeAuthenticationProvider;
import com.monk.security.filter.SmsCodeAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @ClassName SmsCodeSecurityConfig
 * @Description: TODO
 * @Author Monk
 * @Date 2020/4/12
 * @Version V1.0
 **/
@Configuration
public class SmsCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter authenticationFilter = new SmsCodeAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
//        authenticationFilter.setAuthenticationSuccessHandler((AuthenticationSuccessHandler) authenticationFailureHandler);
//        authenticationFilter.setAuthenticationFailureHandler((AuthenticationFailureHandler) authenticationSuccessHandler);

        SmsCodeAuthenticationProvider provider = new SmsCodeAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(provider)
                .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
