package com.monk.security.controller;

import com.monk.security.bean.User;
import com.monk.security.exception.UserNotExistException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<User> queryAllUser(User user) {

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        logger.info("查询全部用户信息，查询到的结果集数量为:{}", users.size());
        return users;
    }

    @GetMapping("/{id:\\d+}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        if(Long.valueOf(id) > 100){
            throw new UserNotExistException(1L);
        }else{
            User user = new User();
            user.setId(1L);
            user.setUserName("jack");
            user.setAge(18);
            logger.info(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
            return user;
        }
    }

    @PostMapping
    public User createUser(User user){
        logger.info("创建的用户信息为：{}", user);
        user.setId(1L);
        return user;
    }

    @PutMapping
    public void updateUser(User user){
        logger.info("更新后的用户信息为：{}", user);
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteUser(@PathVariable Long id){
        logger.info("删除了ID为{}的用户", id);
    }
}
