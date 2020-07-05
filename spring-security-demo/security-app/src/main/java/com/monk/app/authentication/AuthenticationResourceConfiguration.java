package com.monk.app.authentication;

import com.monk.app.authentication.mobile.SmCodeAuthenticationSecurityConfig;
import com.monk.app.authentication.validatecode.ValidateCodeSecurityConfig;
import com.monk.app.constant.SecurityConstant;
import com.monk.app.propertites.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @ClassName AuthenticationResourceConfiguration
 * @Description: TODO
 * @Author Monk
 * @Date 2020/6/17
 * @Version V1.0
 **/
@Configuration
@EnableResourceServer
public class AuthenticationResourceConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    protected AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler customAuthenticationFailedHandler;

    @Autowired
    private SmCodeAuthenticationSecurityConfig smCodeAuthenticationSecurityConfig;

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstant.DEFAULT_UN_AUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailedHandler);

        http.apply(validateCodeSecurityConfig)
                    .and()
                .apply(smCodeAuthenticationSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstant.DEFAULT_UN_AUTHENTICATION_URL,
                        SecurityConstant.DEFAULT_MOBILE_LOGIN_PROCESSING_URL_FORM,
                        "/favicon.ico",
                        SecurityConstant.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getLoginPage(),
                        securityProperties.getBrowser().getRegisterPage(),
                        SecurityConstant.DEFAULT_REGISTER_URL,
                        SecurityConstant.DEFAULT_SIGNOUT_URL,
                        securityProperties.getBrowser().getSession().getInvalidSessionUrl() + ".json",
                        securityProperties.getBrowser().getSession().getInvalidSessionUrl() + ".html")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
