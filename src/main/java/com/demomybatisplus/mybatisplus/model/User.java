package com.demomybatisplus.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data //lombok注解
@AllArgsConstructor
@TableName("t_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String name;
    private Integer age;
    private String email;

    @TableLogic(value = "0")
    private Integer isDeleted;

    public User() {

    }
}
