package com.demomybatisplus.mybatisplus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demomybatisplus.mybatisplus.model.Announcement;
import com.demomybatisplus.mybatisplus.model.User;
import com.demomybatisplus.mybatisplus.model.dto.AnnouncementQueryReq;
import com.demomybatisplus.mybatisplus.model.dto.UserQueryReq;
import com.demomybatisplus.mybatisplus.service.AnnouncementService;
import com.demomybatisplus.mybatisplus.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/announcementManagement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping("/list")
    public ResponseEntity<IPage<Announcement>> queryList(@RequestBody AnnouncementQueryReq req) {
        IPage<Announcement> announcementIPageQueryPOIPage = announcementService.queryList(req);
        return ResponseEntity.ok(announcementIPageQueryPOIPage);
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Announcement announcement) {
        announcementService.saveAnnouncement(announcement);
        return new ResponseEntity<>("Saved Successfully!", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity update(@Valid @RequestBody Announcement announcement) {
        announcementService.updateAnnouncement(announcement);
        return new ResponseEntity<>("Updated Successfully!", HttpStatus.OK);
    }
}
