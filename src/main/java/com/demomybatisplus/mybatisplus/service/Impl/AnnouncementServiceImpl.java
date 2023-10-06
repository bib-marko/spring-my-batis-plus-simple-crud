package com.demomybatisplus.mybatisplus.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demomybatisplus.mybatisplus.exception.ListEmptyException;
import com.demomybatisplus.mybatisplus.mapper.AnnouncementMapper;
import com.demomybatisplus.mybatisplus.mapper.UserMapper;
import com.demomybatisplus.mybatisplus.model.Announcement;
import com.demomybatisplus.mybatisplus.model.User;
import com.demomybatisplus.mybatisplus.model.dto.AnnouncementQueryReq;
import com.demomybatisplus.mybatisplus.service.AnnouncementService;
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
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Autowired
    private AnnouncementService announcementService;
    @Override
    public IPage<Announcement> queryList(AnnouncementQueryReq req) {
        IPage<Announcement> page = new Page<>();
        page.setCurrent(req.getCurrent());
        page.setSize(req.getSize());
        List<Announcement> announcementList = PageUtil.toPage((int) page.getCurrent(), (int) page.getSize(), announcementMapper.queryList(req));
        return page.setRecords(announcementList).setTotal(announcementList.size());
    }

    @Override
    public ResponseEntity<Announcement> saveAnnouncement(Announcement announcement) {
        announcementService.save(announcement);
        return new ResponseEntity<>(announcement, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Announcement> updateAnnouncement(Announcement announcement) {
        announcementMapper.updateById(announcement);
        return new ResponseEntity<>(announcement, HttpStatus.OK);
    }
}
