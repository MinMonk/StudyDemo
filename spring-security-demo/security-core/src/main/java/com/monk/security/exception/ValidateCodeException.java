package com.monk.security.exception;

import org.springframework.security.core.AuthenticationException;


/**
 * @ClassName ImageCodeException
 * @Description: TODO
 * @Author Monk
 * @Date 2020/4/6
 * @Version V1.0
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg){
        super(msg);
    }
}
