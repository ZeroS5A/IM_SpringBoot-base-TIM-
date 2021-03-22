package com.dao;

import com.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    @Select("select * from t_student")
    public List<Student> selAllStudent();

    @Select(
        "Select\n" +
            "t_user.userTimId\n" +
            "From \n" +
            "t_user\n" +
            "Where \n" +
            "userName=#{0}\n" +
            "AND \n" +
            "userPassword=#{1}"
    )
    public String userLogin(String userName, String password);
}
