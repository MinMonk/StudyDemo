package com.monk.security.constant;

/**
 * @ClassName SecurityConstant
 * @Description: TODO
 * @Author Monk
 * @Date 2020/4/6
 * @Version V1.0
 **/
public class SecurityConstant {

    public static final String SESSION_IMAGE_CODE_KEY = "SESSION_CODE_KEY_FOR_IMAGE";
    public static final String SESSION_SMS_CODE_KEY = "SESSION_CODE_KEY_FOR_SMS";
    public static final String DEFAULT_LOGIN_PAGE = "/custom-login.html";
    /**
     * 默认的处理验证码的url前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";


    /**
     * 当请求需要身份认证时，默认跳转的url
     *
     */
    public static final String DEFAULT_UN_AUTHENTICATION_URL = "/authentication/require";
    /**
     * 默认的用户名密码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 手机短信验证方式登录请求处理url
     */
    public static final String MOBILE_LOGIN_PROCESSING_URL_FORM = "/authentication/mobile";

    /**
     * 验证图片验证码时，HTTP请求中默认的携带图片验证码信息的参数名称
     */
    public static final String DEFAULT_PARAMETER_NAME_IMAGE_CODE = "imageCode";

    /**
     * 验证短信验证码时，HTTP请求中默认的携带短信验证码信息的参数名称
     */
    public static final String DEFAULT_PARAMETER_NAME_SMS_CODE = "smsCode";
}
