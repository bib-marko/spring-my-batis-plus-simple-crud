package com.demomybatisplus.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demomybatisplus.mybatisplus.model.Announcement;
import com.demomybatisplus.mybatisplus.model.dto.AnnouncementQueryReq;

import java.util.List;

public interface AnnouncementMapper extends BaseMapper<Announcement> {
    List<Announcement> queryList(AnnouncementQueryReq req);
}
