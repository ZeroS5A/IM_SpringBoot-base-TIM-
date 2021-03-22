package com.server.impl;

import com.bean.Student;
import com.dao.UserDao;
import com.server.UserSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements UserSever {
    @Autowired
    UserDao userDao;

    @Override
    public List<Student> selStudent() {
        return userDao.selAllStudent();
    }

    @Override
    public String getTimId(String userName, String userPassword) {
        return userDao.userLogin(userName,userPassword);
    }
}
