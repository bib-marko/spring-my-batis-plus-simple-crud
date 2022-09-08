package com.demomybatisplus.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demomybatisplus.mybatisplus.mapper.UserMapper;
import com.demomybatisplus.mybatisplus.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class MyBatisPlusApplicationTests {

    @Resource
    private UserMapper userMapper;


//    @Test
//    public void testSelectList(){
//        userMapper.selectList(null).forEach(System.out::println);
//    }

//    @Test
//    public void testInsert(){
//        User user = new User(null, "Zhang San", 23, "zhangsan@atguigu.com");
//        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
//        int result = userMapper.insert(user);
//        log.info("Affected rows："+result);
//        //1475754982694199298
//        log.info("id is automatically obtained："+user.getId());
//    }

    @Test
    public void testDeleteById(){
        //Delete user information by id
        //DELETE FROM user WHERE id=?
        int result = userMapper.deleteById(1475754982694199298L);
        log.info("Affected rows："+result);
    }

    @Test
    public void testDeleteBatchIds(){
        //Batch delete by multiple ids
        //DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> idList = Arrays.asList(1L, 2L, 1567359857713098753L);
        int result = userMapper.deleteBatchIds(idList);
        log.info("Affected rows："+result);
    }

    @Test
    public void testDeleteByMap(){
        //Delete records according to the conditions set in the map collection
        //DELETE FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("age", 21);
        map.put("name", "Sandy");
        int result = userMapper.deleteByMap(map);
        log.info("Affected rows："+result);
    }

//    @Test
//    public void testUpdateById(){
//        User user = new User(5L, "admin", 22, "olivaresmarkanthony@outlook.com");
//        //UPDATE user SET name=?, age=? WHERE id=?
//        int result = userMapper.updateById(user);
//        log.info("Affected rows："+result);
//    }

    @Test
    public void testSelectById(){
        //Query user information by id
        //SELECT id,name,age,email FROM user WHERE id=?
        User user = userMapper.selectById(5L);
        log.info("Selected user: " + user);
    }

    @Test
    public void testSelectBatchIds(){
        //Query multiple user information based on multiple ids
        //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
        List<Long> idList = Arrays.asList(3L, 5L);
        List<User> list = userMapper.selectBatchIds(idList);
        list.forEach(User -> log.info(list.toString()));
    }

    @Test
    public void testSelectByMap(){
        //Query user information through map conditions
        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("age", 22);
        map.put("name", "admin");
        List<User> list = userMapper.selectByMap(map);
        list.forEach(User -> log.info(list.toString()));
    }

    @Test
    public void testSelectList(){
        //Query all user information
        //SELECT id,name,age,email FROM user
        List<User> list = userMapper.selectList(null);
        list.forEach(User -> log.info(list.toString()));
    }










}
