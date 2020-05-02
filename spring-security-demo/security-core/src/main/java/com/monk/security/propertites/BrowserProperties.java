package com.monk.security.propertites;

import com.monk.security.constant.SecurityConstant;
import com.monk.security.validate.bean.LoginResponseType;

/**
 * @ClassName WebProperties
 * @Description: TODO
 * @Author Monk
 * @Date 2020/4/5
 * @Version V1.0
 **/
public class BrowserProperties {

    private String loginPage = SecurityConstant.DEFAULT_LOGIN_PAGE;

    private String signUpUrl = "/imooc-signUp.html";

    private LoginResponseType loginType = LoginResponseType.JSON;

    private int rememberedSeconds = 3600;

    public int getRememberedSeconds() {
        return rememberedSeconds;
    }

    public void setRememberedSeconds(int rememberedSeconds) {
        this.rememberedSeconds = rememberedSeconds;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }
}
