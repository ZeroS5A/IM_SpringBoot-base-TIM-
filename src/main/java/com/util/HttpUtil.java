package com.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class HttpUtil {

    public static JSONObject doGet(String URL) {
        //1:创建一个HttpClient的实例
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //2:创建一个get请求实例
        HttpGet httpGet = new HttpGet(URL);
        //请求的响应:
        CloseableHttpResponse response1 = null;
        try {
            //3:使用HttpClient的实例执行get请求
            response1 = httpclient.execute(httpGet);
            //http请求的状态:404 500 200
            //System.out.println(response1.getStatusLine());
            int statusCode = response1.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                //请求成功:
                HttpEntity entity1 = response1.getEntity();
//                String result = EntityUtils.toString(entity1, "utf-8");
                return new JSONObject().parseObject(EntityUtils.toString(entity1, "utf-8"));
            } else {
                //请求失败
                System.out.println("请求失败......:"+statusCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static JSONObject doPost(String URL, String postData) {
        //1:创建一个HttpClient的实例
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //2:创建一个POST请求实例
        HttpPost httpPost = new HttpPost(URL);
        try {
            httpPost.setEntity(new StringEntity(postData.toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //请求的响应:
        CloseableHttpResponse response1 = null;
        try {
            //3:使用HttpClient的实例执行get请求
            response1 = httpclient.execute(httpPost);
            //http请求的状态:404 500 200
            //System.out.println(response1.getStatusLine());
            int statusCode = response1.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                //请求成功:
                HttpEntity entity1 = response1.getEntity();
//                String result = EntityUtils.toString(entity1, "utf-8");
                return new JSONObject().parseObject(EntityUtils.toString(entity1, "utf-8"));
            } else {
                //请求失败
                System.out.println("请求失败......:"+statusCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static JSONObject postTim(String apiName, String postData) {
//        Random random = new Random();
//        int randNumber = random.nextInt(4294967295 - 0 + 1) + 0;
        String usrSig = "eJwtzNEKgjAYBeB32XXIv*lsE7oJutEKoSnUnbElP6Ebc1gQvXumXp7vHM6HqOMlGo0nGWERkM2cUZs*4ANnbnSHPQ7BN8H6dTDoZ*McapLRBCCRQtJ4aczboTeTc84ZACwasPtbStNECCrZ*oLt9F8pua9yhaw2h3rkFm8vO56gLdS5iNm2aO21K0u858zuyPcHVuk0xQ__";
        return doPost("https://console.tim.qq.com/v4/"+apiName+"?sdkappid=1400498913&identifier=administrator&usersig="+usrSig+"&contenttype=json", postData);
    }
}
