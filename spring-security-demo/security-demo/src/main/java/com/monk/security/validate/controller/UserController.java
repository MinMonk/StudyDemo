package com.monk.security.validate.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.monk.security.bean.User;
import com.monk.security.exception.UserNotExistException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Monk
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);


    @GetMapping("/me")
    public Object getCurrUserInfo(@AuthenticationPrincipal UserDetails userDetails){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("me1")
    public Object me1(Authentication authentication){
        return authentication;
    }

    @GetMapping("me2")
    public Object me2(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> queryAllUser(User user) {

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        logger.info("查询全部用户信息，查询到的结果集数量为:{}", users.size());
        return users;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getUser(@PathVariable Long id) {
        Long userId = Long.valueOf(id);
        if (userId > 100 && userId < 200) {
            throw new UserNotExistException(1L);
        } else if(userId >= 200){
            throw new NullPointerException("-------");
        }else {
            User user = new User();
            user.setId(1L);
            user.setUserName("jack");
            user.setAge(18);
            logger.info(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
            return user;
        }
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        logger.info("创建的用户信息为：{}", user);
        user.setId(1L);
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public void updateUser(@RequestBody User user){
        logger.info("更新后的用户信息为：{}", user);
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteUser(@PathVariable Long id){
        logger.info("删除了ID为{}的用户", id);
    }
}
