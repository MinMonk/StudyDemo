package com.monk.security.config;

import com.monk.security.authentication.CustomAuthenticationFailedHandler;
import com.monk.security.authentication.CustomAuthenticationSuccessHandler;
import com.monk.security.constant.SecurityConstant;
import com.monk.security.filter.ImageCodeFilter;
import com.monk.security.filter.SmsCodeFilter;
import com.monk.security.propertites.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @ClassName WebSecurityConfig
 * @Description: TODO
 * @Author Monk
 * @Date 2020/4/5
 * @Version V1.0
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private CustomAuthenticationFailedHandler failedHandler;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeSecurityConfig smsCodeSecurityConfig;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        // 方式一： 禁用httpBasic认证
        //http.httpBasic().disable();

        // 方式二： 允许所有请求
        //http.authorizeRequests().antMatchers("/").permitAll();

        ImageCodeFilter imageCodeFilter = new ImageCodeFilter();
        imageCodeFilter.setFailureHandler(failedHandler);
        imageCodeFilter.setSecurityProperties(securityProperties);
        imageCodeFilter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setFailureHandler(failedHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class)
                    .formLogin()
                    .loginPage(SecurityConstant.DEFAULT_UN_AUTHENTICATION_URL)
                    .loginProcessingUrl(SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                    .successHandler(successHandler)
                    .failureHandler(failedHandler)
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getWeb().getRememberedSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstant.DEFAULT_UN_AUTHENTICATION_URL,
                        "/favicon.ico",
                        SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getWeb().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .apply(smsCodeSecurityConfig);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
