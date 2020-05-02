package com.monk.security.filter;

import com.monk.security.constant.SecurityConstant;
import com.monk.security.filter.exception.ValidateCodeException;
import com.monk.security.propertites.SecurityProperties;
import com.monk.security.validate.processor.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName ImageCodeFilter
 * @Description: TODO
 * @Author Monk
 * @Date 2020/4/6
 * @Version V1.0
 **/
public class ImageCodeFilter extends OncePerRequestFilter implements InitializingBean {

    public static final Logger logger = LoggerFactory.getLogger(ValidateCodeException.class);

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private AuthenticationFailureHandler failureHandler;

    public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    private Set<String> urls = new HashSet<String>();

    private SecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getValidateCode().getImageCode().getUrls(), ",");
        for (String url : configUrls) {
            urls.add(url);
        }
        urls.add(SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_FORM);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }

        if (action) {
            try {
                validaImageCode(new ServletWebRequest(request));
            } catch (ValidateCodeException ex) {
                logger.error("图形验证码校验失败，返回登陆界面");
                failureHandler.onAuthenticationFailure(request, response, ex);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void validaImageCode(ServletWebRequest request) throws ServletRequestBindingException, ValidateCodeException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, SecurityConstant.SESSION_IMAGE_CODE_KEY);

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (null == codeInSession) {
            throw new ValidateCodeException("验证码不存在");
        }
        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(request, SecurityConstant.SESSION_IMAGE_CODE_KEY);
            throw new ValidateCodeException("验证码已经过期");
        }
        if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, SecurityConstant.SESSION_IMAGE_CODE_KEY);
    }
}
