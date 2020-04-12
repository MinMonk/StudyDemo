package com.monk.security.validate.smscode;

import com.monk.security.propertites.SecurityProperties;
import com.monk.security.validate.CodeGenerator;
import com.monk.security.validate.bean.ValidateCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName SmsCodeGenerator
 * @Description: TODO
 * @Author Monk
 * @Date 2020/4/12
 * @Version V1.0
 **/
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements CodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generateCode(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getValidateCode().getSmsCode().getLength());
        return new ValidateCode(code, securityProperties.getValidateCode().getSmsCode().getExpireIn());
    }
}
