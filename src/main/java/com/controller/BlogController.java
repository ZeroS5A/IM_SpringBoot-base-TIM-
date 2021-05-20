package com.controller;

import com.bean.TBlog;
import com.bean.TBloglike;
import com.bean.TComment;
import com.commom.Result;
import com.server.BlogServer;
import com.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogServer blogServer;
    @Autowired
    TokenUtil tokenUtil;

    @RequestMapping("/getBlogList")
    public Result getBlogList(@RequestParam("userName") String userName){
        Result result= new Result();
        result.setData(blogServer.getBlogList(userName));
        result.setCode(200);
        return result;
    }

    @RequestMapping("/getBlogListById")
    public Result getBlogListById(@RequestHeader("Authorization") String token ,@RequestParam("userName") String userName){
        Result result= new Result();
        if (!tokenUtil.getTokenData(token).getUserName().equals(userName)){
            result.setCode(304);
            return result;
        }
        result.setData(blogServer.getBlogListById(userName));
        result.setCode(200);

        return result;
    }

    @RequestMapping("/insertBlog")
    public Result insertBlog(@RequestHeader("Authorization") String token , @RequestBody TBlog tBlog){
        Result result= new Result();
        if (!tokenUtil.getTokenData(token).getUserName().equals(tBlog.getUserName())){
            result.setCode(304);
            return result;
        }
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
    public Result insertComment(@RequestHeader("Authorization") String token, @RequestBody TComment tComment){
        Result result= new Result();
        if (!tokenUtil.getTokenData(token).getUserName().equals(tComment.getUserName())){
            result.setCode(304);
            return result;
        }
        if (blogServer.insertComment(tComment) == 1){
            result.setCode(200);
            result.setMessage("success");
        }
        else
            result.setCode(400);
        return result;
    }

    @RequestMapping("/deleteComment")
    public Result deleteComment(@RequestHeader("Authorization") String token, @RequestParam("commentId") Integer commentId , @RequestParam("userName") String userName ){
        Result result= new Result();
        if (!tokenUtil.getTokenData(token).getUserName().equals(userName)){
            result.setCode(304);
            return result;
        }
        if (blogServer.deleteCommentById(commentId) == 1){
            result.setCode(200);
            result.setMessage("success");
        }
        else
            result.setCode(400);
        return result;
    }

    @RequestMapping("/deleteBlog")
    public Result deleteBlog(@RequestHeader("Authorization") String token, @RequestParam("blogId") Integer blogId , @RequestParam("userName") String userName){
        Result result= new Result();
        if (!tokenUtil.getTokenData(token).getUserName().equals(userName)){
            result.setCode(304);
            return result;
        }
        if (blogServer.deleteBlogById(blogId) == 1){
            result.setCode(200);
            result.setMessage("success");
        }
        else
            result.setCode(400);
        return result;
    }

    @RequestMapping("/insertLike")
    public Result insertLike(@RequestHeader("Authorization") String token, @RequestBody TBloglike tBloglike){
        Result result= new Result();
        if (!tokenUtil.getTokenData(token).getUserName().equals(tBloglike.getUserName())){
            result.setCode(304);
            return result;
        }
        if (blogServer.insertLike(tBloglike.getBlogId(),tBloglike.getUserName()) == 1){
            result.setCode(200);
            result.setMessage("success");
        }
        else
            result.setCode(400);
        return result;
    }

    @RequestMapping("/deleteLike")
    public Result deleteLike(@RequestHeader("Authorization") String token, @RequestBody TBloglike tBloglike){
        Result result= new Result();
        if (!tokenUtil.getTokenData(token).getUserName().equals(tBloglike.getUserName())){
            result.setCode(304);
            return result;
        }
        if (blogServer.deleteLike(tBloglike.getBlogId(),tBloglike.getUserName()) == 1){
            result.setCode(200);
            result.setMessage("success");
        }
        else
            result.setCode(400);
        return result;
    }
}
