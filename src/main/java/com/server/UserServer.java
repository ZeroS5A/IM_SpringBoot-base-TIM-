package com.server;

import com.bean.Student;
import com.commom.Result;

import java.util.List;
import java.util.Map;

public interface UserServer {
    List<Student> selStudent();

    String getTimId(String userName, String userPassword);

    Result getMailCode(String mailAddress);

    public Result userRegister(Map<String, String> map);

    List<String> getUserRelation(String userID);

    int addUserRelation(String user1, String user2);

    int deleteUserRelation(String user1, String user2);
}
