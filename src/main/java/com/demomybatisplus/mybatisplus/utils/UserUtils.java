package com.demomybatisplus.mybatisplus.utils;

import com.demomybatisplus.mybatisplus.mapper.UserMapper;
import com.demomybatisplus.mybatisplus.model.User;

import java.util.List;

public class UserUtils {

    public static boolean validateUsernameIfExisting(UserMapper userMapper, User user) {
        boolean exitingUser = false;
        List<User> list = userMapper.selectList(null);
        for (int index = 0; index < list.size(); index++){
            if(user.getName().equals(list.get(index).getName())){
                 exitingUser = true;
                 break;
            }
        }
        return exitingUser;
    }

    public static boolean validateIDifExisting(UserMapper userMapper, Long id) {
        boolean exitingUser = false;
        List<User> list = userMapper.selectList(null);
        for (int index = 0; index < list.size(); index++){
            if(list.get(index).getId().equals(id)){
                exitingUser = true;
                break;
            }
        }
        return exitingUser;
    }
}
