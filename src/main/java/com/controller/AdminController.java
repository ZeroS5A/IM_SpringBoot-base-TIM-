package com.controller;

import com.commom.Result;
import com.server.AdminServer;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController extends ExceptionController{
    @Autowired
    AdminServer adminServer;

    @RequiresRoles("admin")
    @PostMapping("/getUserList")
    public Result getUserList(){
        Result result= new Result();
        result.setCode(200);
        result.setData(adminServer.getUserList());
        return result;
    }

    @RequiresRoles("admin")
    @PostMapping("/getBlogList")
    public Result getBlogList(){
        Result result= new Result();
        result.setCode(200);
        result.setData(adminServer.getBlogList());
        return result;
    }
}
