package com.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bean.Token;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenUtil {

    //过期时间设置(30分钟)
    private static final long EXPIRE_TIME = 30*60*1000;
    //验证码过期时间设置(10分钟)
    private static final long MAILEXPIRE_TIME = 10*60*1000;

    //私钥设置(随便乱写的)
    private static final String TOKEN_SECRET = "ByZeroS202002";

    Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

    public String getToken(Map<String,String> map){

        //过期时间和加密算法设置
        Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        //头部信息
        Map<String,Object> header=new HashMap<>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");

        return JWT.create()
                .withHeader(header)
                .withClaim("userName",map.get("userName"))
                .withClaim("role",map.get("role"))
                .withClaim("expirationTime",System.currentTimeMillis()+EXPIRE_TIME)
//                .withExpiresAt(date)
                .sign(algorithm);

    }

    //检查Token，如果错误或者超时，返回false
    public Boolean goodToken(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            if(jwt.getClaim("expirationTime").asLong()<System.currentTimeMillis()){
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }


    //获取验证码token
    public String getMailToken(Integer mailCode){
        //头部信息
        Map<String,Object> header=new HashMap<String, Object>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");

        return JWT.create()
                .withHeader(header)
                .withClaim("mailCode",mailCode)
                .withClaim("expirationTime",System.currentTimeMillis()+MAILEXPIRE_TIME)
                .sign(algorithm);
    }

    public String getMailCode(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("mailCode").asInt().toString();
    }

    public Token getTokenData(String token){
        DecodedJWT jwt = JWT.decode(token);
        Token tk = new Token();

        tk.setUserName(jwt.getClaim("userName").asString());
        tk.setRole(jwt.getClaim("role").asString());
//        tk.setExpirationTime(jwt.getClaim("lastLogin").asDate());

        return tk;
    }
}
