package com.monk.security.propertites;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName SecurityProperties
 * @Description: TODO
 * @Author Monk
 * @Date 2020/4/5
 * @Version V1.0
 **/
@ConfigurationProperties(prefix = "monk.security")
public class SecurityProperties {

    private AppProperties app = new AppProperties();

    private WebProperties web = new WebProperties();

    private ValidateCodeProperties validateCode = new ValidateCodeProperties();

    public ValidateCodeProperties getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(ValidateCodeProperties validateCode) {
        this.validateCode = validateCode;
    }

    public AppProperties getApp() {
        return app;
    }

    public void setApp(AppProperties app) {
        this.app = app;
    }

    public WebProperties getWeb() {
        return web;
    }

    public void setWeb(WebProperties web) {
        this.web = web;
    }
}
