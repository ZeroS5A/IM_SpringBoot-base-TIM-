package com.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.commom.Result;
import com.server.UserSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/getUserRelation")
    public Result getUserRelation(@RequestParam(value="userID",required=false)String userID ){
//        result.setData(userSever.getUserRelation(userID));
        String postData = "{\n" +
                "  \"To_Account\":"+ JSONArray.toJSONString(userSever.getUserRelation(userID))+",\n"+
                "  \"TagList\":\n" +
                "  [\n" +
                "      \"Tag_Profile_IM_Nick\",\n" +
                "      \"Tag_Profile_IM_AllowType\",\n" +
                "      \"Tag_Profile_IM_SelfSignature\",\n" +
                "      \"Tag_Profile_IM_Image\"\n" +
                "  ]\n" +
                "}";
        System.out.println(postData);
        JSONObject res = HttpUtil.postTim("profile/portrait_get",postData);
        System.out.println(res);
        result.setData(res);
        return result;
    }

    @RequestMapping("/addUserRelation")
    public Result addUserRelation(@RequestBody Map user){
        int res = userSever.addUserRelation(user.get("userID").toString(),user.get("userName").toString());
        if (res == -1){
            result.setCode(404);
            result.setMessage("查无此人");
        }
        else if (res == 1){
            result.setCode(200);
        }
        else if (res == 2){
            result.setCode(405);
            result.setMessage("不能添加自己");
        }
        else {
            result.setCode(500);
        }
        return result;
    }

    @RequestMapping("/updateUserProfile")
    public String updateUserProfile(){
        String postdata = "{\n" +
                "    \"From_Account\":\"user1\",\n" +
                "    \"ProfileItem\":\n" +
                "    [\n" +
                "        {\n" +
                "            \"Tag\":\"Tag_Profile_IM_Nick\",\n" +
                "            \"Value\":\"ZeroS\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        System.out.println(postdata);
        JSONObject res = HttpUtil.postTim("profile/portrait_set",postdata);
        System.out.println(res);
        return res.toJSONString();
    }
}
