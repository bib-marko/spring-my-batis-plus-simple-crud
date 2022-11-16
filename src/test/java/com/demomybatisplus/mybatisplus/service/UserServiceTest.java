//package com.demomybatisplus.mybatisplus.service;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
//import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.demomybatisplus.mybatisplus.mapper.UserMapper;
//import com.demomybatisplus.mybatisplus.model.User;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
//@Slf4j
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class UserServiceTest {
//
//    @Autowired
//    private UserService userService;
//
//    @Resource
//    private UserMapper userMapper;
//
//    @Test
//    public void testGetCount(){
//        long count = userService.count();
//        log.info("Total count：" + count);
//    }
//
////    @Test
////    public void testSaveBatch(){
////        // The length of SQL is limited, and it is impossible to insert massive data into a single SQL.
////        // Therefore, MP implements batch insertion in a generic Service instead of a generic Mapper
////        ArrayList<User> users = new ArrayList<>();
////        for (int i = 0; i < 5; i++) {
////            User user = new User();
////            user.getUsername("ybc" + i);
////            user.setAge(20 + i);
////            users.add(user);
////        }
////        //SQL:INSERT INTO t_user ( username, age ) VALUES ( ?, ? )
////        userService.saveBatch(users);
////    }
//
//    @Test
//    public void test01(){
//        //Query user information whose username contains "m", whose age is between 20 and 30, and whose email address is not null
//        //SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE
//        //is_deleted=0 AND (username LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("username", "m")
//                .between("age", 20, 30)
//                .isNotNull("email");
//        List<User> list = userMapper.selectList(queryWrapper);
//        list.forEach(System.out::println);
//    }
//
//    @Test
//    public void test02(){
//        //Query users in descending order of age, and if the ages are the same, sort them in ascending order by id
//        //SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE
//        //is_deleted=0 ORDER BY age DESC,id ASC
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("age")
//                .orderByAsc("id");
//        List<User> users = userMapper.selectList(queryWrapper);
//        users.forEach(System.out::println);
//    }
//
//
//    @Test
//    public void test03(){
//        //delete user with empty email
//        //DELETE FROM t_user WHERE (email IS NULL)
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.isNull("email");
//        //Conditional constructors can also construct conditions for delete statements
//        int result = userMapper.delete(queryWrapper);
//        log.info("Number of rows affected：" + result);
//    }
//
//
//    @Test
//    public void test04() {
//        //Modify the user information (the age is greater than 20 and the username contains a) or the mailbox is null
//        //UPDATE t_user SET age=?, email=? WHERE (username LIKE ? AND age > ? OR email IS NULL)
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper
//                .like("username", "a")
//                .gt("age", 20)
//                .or()
//                .isNull("email");
//        User user = new User();
//        user.setAge(18);
//        user.setEmail("user@atguigu.com");
//        int result = userMapper.update(user, queryWrapper);
//        log.info("Number of rows affected：" + result);
//    }
//
//    @Test
//    public void test05() {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        //Modify the user information that contains "o" in the user name and (age is greater than 20 or the mailbox is not null)
//        //UPDATE t_user SET age=?, email=? WHERE (username LIKE ? AND (age > ? OR email IS NULL))
//        //Logical precedence within lambda expressions
//        queryWrapper
//                .like("username", "o")
//                .and(i -> i.gt("age", 20).or().isNotNull("email"));
//        User user = new User();
//        user.setAge(18);
//        user.setEmail("user@atguigu.com");
//        int result = userMapper.update(user, queryWrapper);
//        log.info("Number of rows affected：" + result);
//    }
//
//    @Test
//    public void test06() {
//        //Query the username and age fields of user information
//        //SELECT username,age FROM t_user
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("username", "age");
//        //selectMaps() returns a list of Map collections, usually used in conjunction with select() to avoid null column values in the User object that have not been queried
//        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
//        maps.forEach(System.out::println);
//    }
//
//    @Test
//    public void test07() {
//        //Query user information with id less than or equal to 3
//        //SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE (id IN (select id from t_user where id <= 3))
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.inSql("id", "select id from t_user where id <= 3");
//        List<User> list = userMapper.selectList(queryWrapper);
//        list.forEach(System.out::println);
//    }
//
//    @Test
//    public void test08() {
//        //Modify the user information (age is greater than 20 or the mailbox not null) and the user name contains a
//        //Assembling set clauses and modifying conditions
//        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
//        //Logical precedence within lambda expressions
//        updateWrapper
//                .set("age", 23)
//                .set("email", "user@outlook.com")
//                .like("username", "a")
//                .and(i -> i.gt("age", 20).or().isNotNull("email"));
//        //The User object must be created here, otherwise autofill cannot be applied. Can be set to null if no autofill
//        //UPDATE t_user SET username=?, age=?,email=? WHERE (username LIKE ? AND (age > ? OR email IS NULL))
//        //User user = new User();
//        //user.setName("张三");
//        //int result = userMapper.update(user, updateWrapper);
//        //UPDATE t_user SET age=?,email=? WHERE (username LIKE ? AND (age > ? OR email IS NULL))
//        int result = userMapper.update(null, updateWrapper);
//        log.info("Affected row: " + result);
//    }
//
//    @Test
//    public void test09() {
//        //Define query conditions, possibly null (user did not enter or select)
//        String username = null;
//        Integer ageBegin = 10;
//        Integer ageEnd = 24;
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        //StringUtils.isNotBlank() Determines whether a string is not empty and not of length 0 and not composed of whitespace
//        if(StringUtils.isNotBlank(username)){
//            queryWrapper.like("username","a");
//        }
//        if(ageBegin != null){
//            queryWrapper.ge("age", ageBegin);
//        }
//        if(ageEnd != null){
//            queryWrapper.le("age", ageEnd);
//        }
//        //SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE (age >= ? AND age <= ?)
//        List<User> users = userMapper.selectList(queryWrapper);
//        users.forEach(System.out::println);
//    }
//
//    @Test
//    public void test09UseCondition() {
//        //Define query conditions, possibly null (user did not enter or select)
//        String username = null;
//        Integer ageBegin = 10;
//        Integer ageEnd = 24;
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        //StringUtils.isNotBlank()判断某字符串是否不为空且长度不为0且不由空白符(whitespace)构成
//        queryWrapper.like(StringUtils.isNotBlank(username), "username", "a")
//                .ge(ageBegin != null, "age", ageBegin)
//                .le(ageEnd != null, "age", ageEnd);
//        //SELECT id,username AS name,age,email,is_deleted FROM t_user WHERE (age >= ? AND age <= ?)
//        List<User> users = userMapper.selectList(queryWrapper);
//        users.forEach(System.out::println);
//    }
//
////    @Test
////    public void test10() {
////        //Define query conditions, may be null (user did not enter)
////        String username = "a";
////        Integer ageBegin = 10;
////        Integer ageEnd = 24;
////        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
////        //Avoid using strings to represent fields, preventing runtime errors
////        queryWrapper
////                .like(StringUtils.isNotBlank(username), User::getName, username)
////                .ge(ageBegin != null, User::getAge, ageBegin)
////                .le(ageEnd != null, User::getAge, ageEnd);
////        List<User> users = userMapper.selectList(queryWrapper);
////        users.forEach(System.out::println);
////    }
//
////    @Test
////    public void test11() {
////        //Assemble the set clause
////        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
////        updateWrapper
////                .set(User::getAge, 26)
////                .set(User::getEmail, "wedinng@gmail.com")
////                .like(User::getName, "o")
////                .and(i -> i.lt(User::getAge, 24).or().isNotNull(User::getEmail)); //Logical precedence within lambda expressions
////        User user = new User();
////        int result = userMapper.update(user, updateWrapper);
////        log.info("Number of rows affected：" + result);
////    }
//
//
//}