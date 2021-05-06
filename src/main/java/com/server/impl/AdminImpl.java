package com.server.impl;

import com.bean.TBlog;
import com.bean.TUser;
import com.dao.AdminDao;
import com.server.AdminServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AdminServer")
public class AdminImpl implements AdminServer {
    @Autowired
    AdminDao adminDao;

    @Override
    public List<TUser> getUserList() {
        return adminDao.getUserList();
    }

    @Override
    public List<TBlog> getBlogList() {
        return adminDao.getBlogList();
    }
}
