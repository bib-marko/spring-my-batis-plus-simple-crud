package com.demomybatisplus.mybatisplus.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demomybatisplus.mybatisplus.exception.ListEmptyException;
import com.demomybatisplus.mybatisplus.exception.UserAlreadyExistException;
import com.demomybatisplus.mybatisplus.exception.UserNotExistException;
import com.demomybatisplus.mybatisplus.mapper.UserMapper;
import com.demomybatisplus.mybatisplus.model.User;
import com.demomybatisplus.mybatisplus.model.dto.UserQueryReq;
import com.demomybatisplus.mybatisplus.service.UserService;
import com.demomybatisplus.mybatisplus.utils.PageUtil;
import com.demomybatisplus.mybatisplus.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<User> saveUser(User user) {
        if(UserUtils.validateUsernameIfExisting(userMapper, user)) {
            throw new UserAlreadyExistException(String.format("Username: %s already exist.", user.getUsername()));
        }
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> updateUser(User user) {
        if(UserUtils.validateUsernameIfExisting(userMapper, user)) {
            throw new UserNotExistException(String.format("User ID number: %s is not existing.", user.getId()));
        }
        userMapper.updateById(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> deleteUser(Long id) {
        if(UserUtils.validateIDifExisting(userMapper, id)) {
            throw new UserNotExistException(String.format("User ID number: %d is not existing.", id));
        }
        userMapper.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public Boolean restoreUser(Long id) {
        if(UserUtils.validateIDifExisting(userMapper, id)) {
            throw new UserNotExistException(String.format("User ID number: %d is not existing.", id));
        }
        return userMapper.restoreUser(id);
    }

    @Override
    public IPage<User> queryList(UserQueryReq req) {
        if(UserUtils.validateListIfEmpty(userMapper)){
            throw new ListEmptyException("Empty List!");
        }
        IPage<User> page = new Page<>();
        page.setCurrent(req.getCurrent());
        page.setSize(req.getSize());
        List<User> userList = PageUtil.toPage((int) page.getCurrent(), (int) page.getSize(), userMapper.queryList(req));
        return page.setRecords(userList).setTotal(userList.size());
    }

    @Override
    public IPage<User> queryDeletedList(UserQueryReq req) {
        if(UserUtils.validateListIfEmpty(userMapper)){
            throw new ListEmptyException("Empty List!");
        }
        IPage<User> page = new Page<>();
        page.setCurrent(req.getCurrent());
        page.setSize(req.getSize());
        List<User> deletedUserList = PageUtil.toPage((int) page.getCurrent(), (int) page.getSize(), userMapper.queryDeletedList(req));
        return page.setRecords(deletedUserList).setTotal(deletedUserList.size());
    }

}