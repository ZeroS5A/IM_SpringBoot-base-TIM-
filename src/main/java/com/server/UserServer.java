package com.server;

import com.bean.Token;
import com.bean.TUser;
import com.commom.Result;

import java.util.List;
import java.util.Map;

public interface UserServer {
    List<Token> selStudent();

    TUser getTimId(String userName, String userPassword);

    Result getMailCode(String mailAddress);

    Result updateEmail(String userId, Map<String, String> map);

    Result updatePassWd(String userId, Map<String,String> map);

    Result userRegister(Map<String, String> map);

    Result getUserData (String userID);

    List<String> getUserRelation(String userID);

    int addUserRelation(String user1, String user2);

    int deleteUserRelation(String user1, String user2);
}
