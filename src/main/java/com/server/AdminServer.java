package com.server;

import com.bean.TBlog;
import com.bean.TUser;

import java.util.List;

public interface AdminServer {
    List<TUser> getUserList(String userName);

    List<TBlog> getBlogList();

    Integer banUser(String userName);

    Integer unBanUser(String userName);

    Integer resetPw(String userName);

    List<String> selectUserName (String userName);
}
