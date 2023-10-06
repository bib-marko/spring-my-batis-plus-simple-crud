package com.demomybatisplus.mybatisplus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demomybatisplus.mybatisplus.model.User;
import com.demomybatisplus.mybatisplus.model.dto.UserQueryReq;
import org.springframework.http.ResponseEntity;


public interface UserService extends IService<User> {

    ResponseEntity<User> saveUser(User user);
    ResponseEntity<User> updateUser(User user);
    ResponseEntity<User> deleteUser(Long id);
    Boolean restoreUser(Long id);
    IPage<User> queryList(UserQueryReq req);
    IPage<User> queryDeletedList(UserQueryReq req);
}
