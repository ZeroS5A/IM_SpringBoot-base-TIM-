package com.server.impl;

import com.bean.Student;
import com.commom.Result;
import com.commom.ResultStatus;
import com.dao.UserDao;
import com.server.UserServer;
import com.util.MailUtil;
import com.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserImpl implements UserServer {
    @Autowired
    UserDao userDao;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    MailUtil mailUtil;


    @Override
    public List<Student> selStudent() {
        return userDao.selAllStudent();
    }

    @Override
    public String getTimId(String userName, String userPassword) {
        return userDao.userLogin(userName,userPassword);
    }

    //获取验证码
    @Override
    public Result getMailCode(String mailAddress) {
        Result result = new Result();
        try {
            //生成随机码
//            Integer mailCode = (int)((Math.random() * 9 + 1) * 100000);
            Integer mailCode = 123456;
            //加工成token
            result.setMessage(tokenUtil.getMailToken(mailCode));
            //发送邮件
//            System.out.println("验证码："+mailCode);
//            if (userDao.checkEmail(mailAddress)==0){
//                mailUtil.sendSimpleMail(mailAddress,"ZIM","欢迎注册！你的验证码是："+mailCode+"，请在十分钟内使用");
//            }else {
//                result.setCode(302);
//                result.setMessage("hadUsed");
//            }
            result.setCode(200);
            return result;
        }catch (Exception e){
            result.setResult(ResultStatus.SERVERERR);
            return result;
        }
    }

    @Override
    public Result userRegister(Map<String, String> map) {
        Result result = new Result();
        //验证验证码正确且未过期
        if (tokenUtil.getMailCode(map.get("token")).equals(map.get("code")) && tokenUtil.goodToken(map.get("token"))){
            //验证是否用户名已被注册
            if (userDao.checkUserName(map.get("userName"))== null){
                if (userDao.userRegister(map.get("userName"),map.get("passwd"),map.get("email")) == 1){
                    result.setMessage("注册成功！");
                    result.setCode(200);
                    return result;
                }else {
                    result.setResult(ResultStatus.SERVERERR);
                    return result;
                }
            }else {
                result.setCode(301);
                result.setMessage("该用户名已被注册！");
                return result;
            }
        }else {
            result.setCode(302);
            result.setMessage("验证码错误或者已经过期！");
            return result;
        }
    }

    @Override
    public List<String> getUserRelation(String userID) {
        return userDao.gerUserRelation(userID);
    }

    @Override
    public int addUserRelation(String user1, String user2) {

        String timId = userDao.getUserTimID(user2);
        if (timId == null){
            return -1;
        }
        if (timId.equals(user1)){
            return 2;
        }
        try {
            return userDao.addUserRelation(user1,timId);
        }catch (Exception e){
            return -2;
        }

    }

    @Override
    public int deleteUserRelation(String user1, String user2) {
        return userDao.deleteRelationShip(user1, user2);
    }
}
