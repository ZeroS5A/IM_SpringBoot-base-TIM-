package com.dao;

import com.bean.TBlog;
import com.bean.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminDao {

    @Select("SELECT * FROM `t_user`")
    List<TUser> getUserList();

    @Select("SELECT * FROM `t_blog`")
    List<TBlog> getBlogList();
}
