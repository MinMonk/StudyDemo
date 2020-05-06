package com.monk.security.social.wechat.config;

import com.monk.security.propertites.SecurityProperties;
import com.monk.security.propertites.WeChatProperties;
import com.monk.security.social.wechat.connect.WeChatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @ClassName WeChatAutoConfig
 * @Description: TODO
 * @Author Monk
 * @Date 2020/5/4
 * @Version V1.0
 **/
@Configuration
@ConditionalOnProperty(prefix = "monk.security.social.wechat", name = "app-id")
public class WeChatAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeChatProperties weChatProperties = securityProperties.getSocial().getWechat();
        return new WeChatConnectionFactory(weChatProperties.getProviderId(), weChatProperties.getAppId(), weChatProperties.getAppSecret());
    }
}
