package com.server;

import com.bean.Student;

import java.util.List;

public interface UserSever {
    List<Student> selStudent();

    String getTimId(String userName, String userPassword);
}
