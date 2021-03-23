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

    @Override
    public List<String> getUserRelation(String userID) {
        return userDao.gerUserRelation(userID);
    }

    @Override
    public int addUserRelation(String user1, String user2) {

        String timId = userDao.getUserTimID(user2);
        if (timId == null){
            return -1;
        }
        if (timId.equals(user1)){
            return 2;
        }
        try {
            return userDao.addUserRelation(user1,timId);
        }catch (Exception e){
            return -2;
        }

    }
}
