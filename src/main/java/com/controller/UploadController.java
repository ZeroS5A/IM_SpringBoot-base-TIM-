package com.controller;

import com.commom.Result;
import com.server.UploadServer;
import com.util.TokenUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/upload")
public class UploadController extends ExceptionController{

    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    UploadServer uploadServer;

    @RequiresRoles(value={"admin","user"},logical = Logical.OR)
    @RequestMapping("/image")
    public Result upLoadAvatar(
            @RequestParam("userID") String userID,
            @RequestParam("image") MultipartFile image,
            @RequestParam("type") String type
            ){
        System.out.println(userID);
        return uploadServer.uploadImage(userID, image, type);
    }
}
