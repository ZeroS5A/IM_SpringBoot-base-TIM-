package com.controller;

import com.commom.Result;
import com.commom.ResultStatus;
import com.dao.StudentDao;
import com.server.StudentSever;
import com.server.TestServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    TestServer testServer;
    @Autowired
    StudentSever studentSever;

    @RequestMapping("/hello")
    public String hello(){
        return testServer.test();
    }

    @RequestMapping("/dbtest")
    public Result datest(){
        Result result=new Result(ResultStatus.SUCCESS,studentSever.selStudent());
        return result;
    }
}
