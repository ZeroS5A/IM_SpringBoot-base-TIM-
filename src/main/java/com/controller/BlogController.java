package com.controller;

import com.bean.TBlog;
import com.bean.TBloglike;
import com.bean.TComment;
import com.bean.TUser;
import com.commom.Result;
import com.server.BlogServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogServer blogServer;

    @RequestMapping("/getBlogList")
    public Result getBlogList(@RequestParam("userName") String userName){
        Result result= new Result();
        result.setData(blogServer.getBlogList(userName));
        return result;
    }

    @RequestMapping("/getBlogListById")
    public Result getBlogListById(@RequestParam("userName") String userName){
        Result result= new Result();
        result.setData(blogServer.getBlogListById(userName));
        return result;
    }

    @RequestMapping("/insertBlog")
    public Result insertBlog(@RequestBody TBlog tBlog){
        Result result= new Result();
        if (blogServer.insertBlog(tBlog) == 1){
            result.setCode(200);
            result.setMessage("success");
        }
        else
            result.setCode(400);
        return result;
    }

    @RequestMapping("/getCommentList")
    public Result getCommentList(@RequestParam("blogId") String blogId ){
        Result result= new Result();
        result.setData(blogServer.getCommentList(blogId));
        return result;
    }

    @RequestMapping("/insertComment")
    public Result insertComment(@RequestBody TComment tComment){
        Result result= new Result();
        if (blogServer.insertComment(tComment) == 1){
            result.setCode(200);
            result.setMessage("success");
        }
        else
            result.setCode(400);
        return result;
    }

    @RequestMapping("/deleteComment")
    public Result deleteComment(@RequestParam("commentId") Integer commentId ){
        Result result= new Result();
        if (blogServer.deleteCommentById(commentId) == 1){
            result.setCode(200);
            result.setMessage("success");
        }
        else
            result.setCode(400);
        return result;
    }

    @RequestMapping("/deleteBlog")
    public Result deleteBlog(@RequestParam("blogId") Integer blogId ){
        Result result= new Result();
        if (blogServer.deleteBlogById(blogId) == 1){
            result.setCode(200);
            result.setMessage("success");
        }
        else
            result.setCode(400);
        return result;
    }

    @RequestMapping("/insertLike")
    public Result insertLike(@RequestBody TBloglike tBloglike){
        Result result= new Result();
        if (blogServer.insertLike(tBloglike.getBlogId(),tBloglike.getUserName()) == 1){
            result.setCode(200);
            result.setMessage("success");
        }
        else
            result.setCode(400);
        return result;
    }

    @RequestMapping("/deleteLike")
    public Result deleteLike(@RequestBody TBloglike tBloglike){
        Result result= new Result();
        if (blogServer.deleteLike(tBloglike.getBlogId(),tBloglike.getUserName()) == 1){
            result.setCode(200);
            result.setMessage("success");
        }
        else
            result.setCode(400);
        return result;
    }
}
