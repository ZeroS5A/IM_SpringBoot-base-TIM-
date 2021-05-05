package com.server.impl;

import com.bean.TBlog;
import com.bean.TComment;
import com.dao.BlogDao;
import com.server.BlogServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BlogServer")
public class BlogImpl implements BlogServer {

    @Autowired
    BlogDao blogDao;

    @Override
    public List<TBlog> getBlogList(String userName) {
        return blogDao.getBlogList(userName);
    }

    @Override
    public List<TBlog> getBlogListById(String userName) {
        return blogDao.getBlogListById(userName);
    }

    @Override
    public Integer insertBlog(TBlog tBlog) {
        Integer blogId = blogDao.insertBlog(tBlog);
        if (blogId != 0){
            return 1;
        }
        else
            return 0;
    }

    @Override
    public List<TComment> getCommentList(String blogId) {
        return blogDao.getCommentList(blogId);
    }

    @Override
    public Integer insertComment(TComment tComment) {
        Integer commentId = blogDao.insertComment(tComment);
        if (commentId != 0){
            return 1;
        }
        else
            return 0;
    }

    @Override
    public Integer deleteCommentById(Integer commentId) {
        Integer i = blogDao.deleteCommentById(commentId);
        if (i != 0){
            return 1;
        }
        else
            return 0;
    }

    @Override
    public Integer deleteBlogById(Integer blogId) {
        Integer i = blogDao.deleteBlogById(blogId);
        if (i != 0){
            return 1;
        }
        else
            return 0;
    }

    @Override
    public Integer insertLike(Integer blogId, String userName) {
        Integer i = blogDao.insertLike(blogId,userName);
        if (i != 0){
            return 1;
        }
        else
            return 0;
    }

    @Override
    public Integer deleteLike(Integer blogId, String userName) {
        Integer i = blogDao.deleteLike(blogId,userName);
        if (i != 0){
            return 1;
        }
        else
            return 0;
    }
}
