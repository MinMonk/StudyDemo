package com.monk.security.code;

import com.monk.security.validate.bean.ImageCode;
import com.monk.security.validate.CodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName CustomImageCodeGenerator
 * @Description: TODO
 * @Author Monk
 * @Date 2020/4/6
 * @Version V1.0
 **/
//@Component("imageCodeGenerator")
public class CustomImageCodeGenerator implements CodeGenerator {

    @Override
    public ImageCode generateCode(ServletWebRequest request) {
        System.out.println("自定义验证码生成器");
        return null;
    }
}
