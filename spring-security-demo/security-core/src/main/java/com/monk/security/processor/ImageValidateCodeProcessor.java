package com.monk.security.processor;

import com.monk.security.validate.bean.ImageCode;
import com.monk.security.validate.bean.ValidateCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @ClassName ImageCodeProcessor
 * @Description: TODO
 * @Author Monk
 * @Date 2020/4/12
 * @Version V1.0
 **/
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {


    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getBufferedImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
