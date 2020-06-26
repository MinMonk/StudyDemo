package com.monk.app.authentication;

import com.monk.app.authentication.mobile.SmCodeAuthenticationSecurityConfig;
import com.monk.app.constant.SecurityConstant;
import com.monk.app.propertites.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @ClassName AuthenticationServerConfiguration
 * @Description: TODO
 * @Author Monka
 * @Date 2020/6/17
 * @Version V1.0
 **/
@Configuration
@EnableAuthorizationServer
public class AuthenticationServerConfiguration extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private TokenStore redisTokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(redisTokenStore)
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("monkDemo")
                .secret("monkSecret")
                .accessTokenValiditySeconds(3200)
                .authorizedGrantTypes("password", "custom","authorization_code")
                .scopes("all");
    }
}
