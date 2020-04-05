package com.monk.security.exception;

/**
 * @ClassName UserNotExistException
 * @Description: TODO
 * @Author Monk
 * @Date 2020/4/5
 * @Version V1.0
 **/
public class UserNotExistException extends RuntimeException {

    private Long userId;

    public UserNotExistException(Long userid){
        super("user is not exist");
        this.userId = userid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
