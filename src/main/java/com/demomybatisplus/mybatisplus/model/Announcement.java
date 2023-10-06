package com.demomybatisplus.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.Timestamp;

import java.util.Date;

@Data //lombok注解
@AllArgsConstructor
@TableName("t_announcement")
public class Announcement {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String description;

    @TableLogic(value = "0")
    private Integer isDeleted;

    private String reasonForDeleting;

    private Timestamp createdAt;
    private Timestamp updatedAt;

}
