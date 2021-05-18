package com.server;

import com.bean.TBlog;
import com.bean.TUser;

import java.util.List;

public interface AdminServer {
    List<TUser> getUserList();

    List<TBlog> getBlogList();

    Integer banUser(String userName);

    Integer unBanUser(String userName);

    Integer resetPw(String userName);
}
