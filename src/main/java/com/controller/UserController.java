package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.commom.Result;
import com.server.UserSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.util.TLSSigAPIv2;
import com.util.HttpUtil;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserSever userSever;
    @Autowired
    private TLSSigAPIv2 tlsSigAPIv2;
    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    Result result;

    @RequestMapping("/userLogin")
    public Result userLogin(@RequestBody Map userData){
        String timId = userSever.getTimId(userData.get("userID").toString(),userData.get("userPW").toString());
        System.out.println(timId);
        if (timId==null){
            result.setCode(4105);
            result.setMessage("用户不存在或者密码错误！");
            return result;
        }
        else {
            result.setCode(200);
            Map<String,String> map =new HashMap<String, String>();
            map.put("timId",timId);
            map.put("userSig",tlsSigAPIv2.genUserSig(timId, 86400));
            result.setData(map);
            return result;
        }
    }

    @RequestMapping("/testpost")
    public String testpost(){
        JSONObject postDat = new JSONObject();
        postDat.put("KEY1", "VALUE1");
        postDat.put("KEY2", "VALUE2");
        JSONObject res = HttpUtil.doPost("https://console.tim.qq.com/v4/im_open_login_svc/account_import?sdkappid=88888888&identifier=admin&usersig=xxx&random=99999999&contenttype=json",postDat);
        System.out.println(res);
        return res.toJSONString();
    }
}
