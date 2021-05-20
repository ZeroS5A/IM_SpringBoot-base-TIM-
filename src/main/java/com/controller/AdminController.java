package com.controller;

import com.alibaba.fastjson.*;
import com.bean.TUser;
import com.commom.Result;
import com.server.AdminServer;
import com.server.BlogServer;
import com.util.HttpUtil;
import com.util.TokenUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController extends ExceptionController{
    @Autowired
    AdminServer adminServer;
    @Autowired
    BlogServer blogServer;
    @Autowired
    TokenUtil tokenUtil;

    @RequiresRoles("admin")
    @GetMapping("/getUserList")
    public Result getUserList(@RequestParam(value="userName",required=true) String userName){
        Result result= new Result();
        result.setCode(200);

        List<TUser> userList = adminServer.getUserList(userName);
        List<String> userIDList = new ArrayList<>();

        for (TUser tUser : userList) {
            userIDList.add(tUser.getUserTimId());
        }

        String postData = "{\n" +
                "  \"To_Account\":"+ JSON.toJSONString(userIDList)+"\n"+
                "}";
        System.out.println(postData);
        JSONObject res = HttpUtil.postTim("openim/querystate",postData);
        System.out.println(res);

        Map<String,Object>  map = new HashMap<>();
        map.put("userList",userList);
        map.put("timOnlineData",res);
        result.setData(map);
        return result;
    }

    @RequiresRoles("admin")
    @PostMapping("/getBlogList")
    public Result getBlogList(@RequestParam("userName") String userName){
        Result result= new Result();
        result.setCode(200);
        result.setData(blogServer.getBlogList(userName));
        return result;
    }

    @RequiresRoles("admin")
    @RequestMapping("/getBlogListById")
    public Result getBlogListById(@RequestParam("userName") String userName){
        Result result= new Result();
        result.setData(blogServer.getBlogListById(userName));
        result.setCode(200);

        return result;
    }

    @RequiresRoles("admin")
    @GetMapping("/banUser")
    public Result banUser(@RequestParam(value="userName",required=true) String userName){
        Result result= new Result();
        result.setCode(200);

        // 强制用户下线
        String postData = "{\n" +
                "  \"Identifier\":\""+ userName +"\""+
                "}";
        System.out.println(postData);
        JSONObject res = HttpUtil.postTim("im_open_login_svc/kick",postData);
        System.out.println(res);

        result.setData(adminServer.banUser(userName));
        return result;
    }

    @RequiresRoles("admin")
    @GetMapping("/unBanUser")
    public Result unBanUser(@RequestParam(value="userName",required=true) String userName){
        Result result= new Result();
        result.setCode(200);
        result.setData(adminServer.unBanUser(userName));
        return result;
    }

    @RequiresRoles("admin")
    @GetMapping("/resetUserPw")
    public Result resetUserPw(@RequestParam(value="userName",required=true) String userName){
        Result result= new Result();
        result.setCode(200);
        result.setData(adminServer.resetPw(userName));
        return result;
    }

    @RequiresRoles("admin")
    @GetMapping("/deleteUser")
    public Result deleteUser(@RequestParam(value="userName",required=true) String userName){
        Result result= new Result();
        result.setCode(200);
        result.setData(adminServer.unBanUser(userName));
        return result;
    }

    @RequiresRoles("admin")
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

    @RequiresRoles("admin")
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

    @RequiresRoles("admin")
    @GetMapping("/getUserNameList")
    public Result getUserNameList(@RequestParam(value="userName",required=true) String userName){
        Result result= new Result();
        result.setCode(200);
        result.setData(adminServer.selectUserName(userName));
        return result;
    }
}
