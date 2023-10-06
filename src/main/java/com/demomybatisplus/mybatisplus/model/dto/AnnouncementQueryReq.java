package com.demomybatisplus.mybatisplus.model.dto;

import lombok.Data;

@Data
public class AnnouncementQueryReq {

    private Long id;
    private String title;
    private String description;
    private Integer isDeleted;
    private String reasonForDeleting;
    private Integer current = 0;
    private Integer size = 10;
}
