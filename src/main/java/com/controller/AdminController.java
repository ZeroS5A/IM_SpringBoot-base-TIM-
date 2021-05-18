package com.controller;

import com.alibaba.fastjson.*;
import com.bean.TUser;
import com.commom.Result;
import com.server.AdminServer;
import com.util.HttpUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

        List<TUser> userList = adminServer.getUserList();
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
    public Result getBlogList(){
        Result result= new Result();
        result.setCode(200);
        result.setData(adminServer.getBlogList());
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
}
