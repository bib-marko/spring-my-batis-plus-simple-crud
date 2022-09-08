package com.demomybatisplus.mybatisplus.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demomybatisplus.mybatisplus.model.User;
import com.demomybatisplus.mybatisplus.model.dto.UserQueryReq;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {
    boolean restoreUser(Long id);
    List<User> queryList(IPage<User> userQueryPOIPage, UserQueryReq req);
    List<User> queryDeletedList(IPage<User> userQueryPOIPage, UserQueryReq req);
}
