package com.dao;

import com.bean.TBlog;
import com.bean.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface AdminDao {

    @Select("SELECT * FROM `t_user` WHERE t_user.userName LIKE #{1}")
    List<TUser> getUserList(String userName);

    @Select("SELECT * FROM `t_blog`")
    List<TBlog> getBlogList();

    @Update("UPDATE t_user SET isban = 1 WHERE userName =  #{1}")
    Integer banUser(String userName);

    @Update("UPDATE t_user SET isban = 0 WHERE userName =  #{1}")
    Integer unBanUser(String userName);

    @Update("UPDATE t_user SET userPassword = 'e10adc3949ba59abbe56e057f20f883e' WHERE userName =  #{1}")
    Integer resetPw(String userName);

    @Select("SELECT\n" +
            "	t_user.userName\n" +
            "FROM\n" +
            "	t_user\n" +
            "WHERE\n" +
            "	t_user.userName LIKE #{1}")
    List<String> selectUserName (String userName);
}
