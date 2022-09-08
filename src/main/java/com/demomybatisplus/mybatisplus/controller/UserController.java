package com.demomybatisplus.mybatisplus.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demomybatisplus.mybatisplus.mapper.UserMapper;
import com.demomybatisplus.mybatisplus.model.User;
import com.demomybatisplus.mybatisplus.model.dto.UserQueryReq;
import com.demomybatisplus.mybatisplus.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/userManagement")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    public ResponseEntity<IPage<User>> queryList(@RequestBody UserQueryReq req) {
        IPage<User> userIPageQueryPOIPage = userService.queryList(req);
        return ResponseEntity.ok(userIPageQueryPOIPage);
    }

    @PostMapping("/list/deleted")
    public ResponseEntity<IPage<User>> queryDeletedList(@RequestBody UserQueryReq req) {
        IPage<User> userIPageQueryPOIPage = userService.queryDeletedList(req);
        return ResponseEntity.ok(userIPageQueryPOIPage);
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>("Saved Successfully!", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity update(@Valid @RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>("Updated Successfully!", HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("Deleted Successfully!", HttpStatus.OK);
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity restore(@PathVariable Long id) {
        return new ResponseEntity<>(userService.restoreUser(id) ? "Restored User Successfully!" : "Error Occurs!", HttpStatus.OK);
    }

}
