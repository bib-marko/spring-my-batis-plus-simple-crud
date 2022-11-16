package com.demomybatisplus.mybatisplus.model.dto;


import lombok.Data;

@Data
public class UserQueryReq {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Integer isDeleted;
    private Integer current = 0;
    private Integer size = 10;
}
