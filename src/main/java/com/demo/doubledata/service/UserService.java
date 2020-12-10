package com.demo.doubledata.service;


import com.demo.doubledata.entity.User;

import java.util.List;

public interface UserService {
    List<User> list();

    List list2();

    void add(User user);

    void add2(User user);

    void shiwu();

    void cutA();
}
