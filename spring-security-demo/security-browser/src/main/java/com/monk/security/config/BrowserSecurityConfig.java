package com.monk.security.config;

import com.monk.security.authentication.AbstractChannelSecurityConfig;
import com.monk.security.authentication.CustomAuthenticationFailedHandler;
import com.monk.security.authentication.CustomAuthenticationSuccessHandler;
import com.monk.security.authentication.image.ImageCodeAuthenticationSecurityConfig;
import com.monk.security.authentication.mobile.SmCodeAuthenticationSecurityConfig;
import com.monk.security.constant.SecurityConstant;
import com.monk.security.propertites.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig{

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmCodeAuthenticationSecurityConfig smCodeAuthenticationSecurityConfig;

    @Autowired
    private ImageCodeAuthenticationSecurityConfig imageCodeAuthenticationSecurityConfig;

    @Autowired
    private CustomAuthenticationFailedHandler failedHandler;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }


    /*@Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPage = new ErrorPage[3];
        errorPage[0] = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
        errorPage[1] = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");
        errorPage[2] = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405.html");
        registry.addErrorPages(errorPage);
    }*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        // 方式一： 禁用httpBasic认证
        //http.httpBasic().disable();

        // 方式二： 允许所有请求
        //http.authorizeRequests().antMatchers("/").permitAll();

        // 1. 加载通用的密码登录配置
        applyPasswordAuthenticationConfig(http);

        http.apply(smCodeAuthenticationSecurityConfig)
                .and()
            .apply(imageCodeAuthenticationSecurityConfig)
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberedSeconds())
                .userDetailsService(userDetailsService)
                .and()
            .authorizeRequests()
            .antMatchers(
                    SecurityConstant.DEFAULT_UN_AUTHENTICATION_URL,
                    SecurityConstant.DEFAULT_MOBILE_LOGIN_PROCESSING_URL_FORM,
                    "/favicon.ico",
                    SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                    securityProperties.getBrowser().getLoginPage())
                .permitAll()
            .anyRequest()
            .authenticated()
                .and()
            .csrf().disable();
    }
}
