package com.dao;

import com.bean.TBlog;
import com.bean.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminDao {

    @Select("SELECT * FROM `t_user`")
    List<TUser> getUserList();

    @Select("SELECT * FROM `t_blog`")
    List<TBlog> getBlogList();

    @Update("UPDATE t_user SET isban = 1 WHERE userName =  #{1}")
    Integer banUser(String userName);

    @Update("UPDATE t_user SET isban = 0 WHERE userName =  #{1}")
    Integer unBanUser(String userName);

    @Update("UPDATE t_user SET userPassword = 123456 WHERE userName =  #{1}")
    Integer resetPw(String userName);
}
