package com.demomybatisplus.mybatisplus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demomybatisplus.mybatisplus.model.Announcement;
import com.demomybatisplus.mybatisplus.model.User;
import com.demomybatisplus.mybatisplus.model.dto.AnnouncementQueryReq;
import org.springframework.http.ResponseEntity;

public interface AnnouncementService extends IService<Announcement> {
    IPage<Announcement> queryList(AnnouncementQueryReq req);

    ResponseEntity<Announcement> saveAnnouncement(Announcement announcement);

    ResponseEntity<Announcement>  updateAnnouncement(Announcement announcement);
}
