package com.monk.security.config;

import com.monk.security.authentication.AbstractChannelSecurityConfig;
import com.monk.security.authentication.CustomAuthenticationFailedHandler;
import com.monk.security.authentication.CustomAuthenticationSuccessHandler;
import com.monk.security.authentication.mobile.SmCodeAuthenticationSecurityConfig;
import com.monk.security.authentication.validatecode.ValidateCodeSecurityConfig;
import com.monk.security.constant.SecurityConstant;
import com.monk.security.propertites.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

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

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmCodeAuthenticationSecurityConfig smCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer customSocialSecurityConfig;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        // 方式一： 禁用httpBasic认证
        //http.httpBasic().disable();

        // 方式二： 允许所有请求
        //http.authorizeRequests().antMatchers("/").permitAll();

        // 1. 加载通用的密码登录配置
        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                .and()
            .apply(smCodeAuthenticationSecurityConfig)
                .and()
            .apply(customSocialSecurityConfig)
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
                    securityProperties.getBrowser().getLoginPage(),
                    securityProperties.getBrowser().getRegisterPage(),
                    SecurityConstant.DEFAULT_REGISTER_URL)
                .permitAll()
            .anyRequest()
            .authenticated()
                .and()
            .csrf().disable();
    }
}
