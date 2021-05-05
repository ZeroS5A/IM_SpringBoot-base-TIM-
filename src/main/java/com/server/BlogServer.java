package com.server;

import com.bean.TBlog;
import com.bean.TComment;

import java.util.List;

public interface BlogServer {
    List<TBlog> getBlogList(String userName);

    List<TBlog> getBlogListById(String userName);

    Integer insertBlog(TBlog tBlog);

    List<TComment> getCommentList(String blogId);

    Integer insertComment(TComment tComment);

    Integer deleteCommentById(Integer commentId);

    Integer deleteBlogById(Integer blogId);

    Integer insertLike(Integer blogId,String userName);

    Integer deleteLike(Integer blogId,String userName);


}
