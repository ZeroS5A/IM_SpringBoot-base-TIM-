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
    public List<TUser> getUserList(String userName) {
        return adminDao.getUserList('%'+userName+'%');
    }

    @Override
    public List<TBlog> getBlogList() {
        return adminDao.getBlogList();
    }

    @Override
    public Integer banUser(String userName) {
        return adminDao.banUser(userName);
    }

    @Override
    public Integer unBanUser(String userName) {
        return adminDao.unBanUser(userName);
    }

    @Override
    public Integer resetPw(String userName) {
        return adminDao.resetPw(userName);
    }

    @Override
    public List<String> selectUserName(String userName) {
        return adminDao.selectUserName('%'+userName+'%');
    }
}
