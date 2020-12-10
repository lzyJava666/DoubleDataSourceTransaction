package com.demo.doubledata.service;


import com.demo.doubledata.entity.User;
import com.demo.doubledata.mapper.test1.User1Mapper;
import com.demo.doubledata.mapper.test2.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    User1Mapper user1Mapper;

    @Autowired
    User2Mapper user2Mapper;

    @Override
    public List<User> list() {
        return user1Mapper.selectAll();
    }


    @Override
    public List list2() {
        //CutDataSource.useDataSource("db2DataSource");
        return user2Mapper.selectAll();
    }

    @Override
    public void add(User user) {
        user1Mapper.insert(user);
    }


    @Override
    public void add2(User user) {
        user1Mapper.insert(user);
    }
    @Transactional
    @Override
    public void shiwu() {
        User user=new User();
        user.setName("事务222");
        user1Mapper.insert(user);
        User u=new User();
        int insert = user2Mapper.insert(u);
    }

    @Transactional
    @Override
    public void cutA() {

        User user=new User();
        user.setName("事务111");
        user1Mapper.insert(user);
        //CutDataSource.useDataSource("db2DataSource");
        User u=new User();
        u.setName("123");
        user2Mapper.insert(u);
    }
}
