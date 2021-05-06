package com.server.impl;

import com.bean.TFile;
import com.commom.Result;
import com.dao.FileDao;
import com.dao.UserDao;
import com.server.UploadServer;
import org.apache.commons.io.IOUtils;
import org.assertj.core.internal.cglib.asm.$Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;

@Service("UploadServer")
public class UploadImpl implements UploadServer {
    @Autowired
    FileDao fileDao;
    @Autowired
    UserDao userDao;

    @Override
    public Result uploadImage(String userId, MultipartFile file, String type) {
        Result result = new Result();

        try {
            //生成MD5
            byte[] uploadBytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            String hashString = new BigInteger(1, digest).toString(16);
            System.out.println(hashString);

            // 创建文件名
            String imgName = hashString + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            // 连接次数过多使用StringBuffer连接节省开销
            StringBuffer imgUrl = new StringBuffer();
            StringBuilder imgPath = new StringBuilder();

            // 线上环境
            imgUrl.append("http://im.lczeros.cn/ZimData/");
            imgPath.append("/root/Zim/ZimData/");

            // 本地环境
//            imgPath.append("/ZimData/");
//            imgUrl.append("http://localhost/ZimData/");


            // 区分不同的类别 服务器路径
            switch (type){
                case "updateAvatar":
                    imgUrl.append("images/avatar/");
                    imgPath.append("images/avatar");
                    break;
                case "blogImg":
                    imgUrl.append("images/blogImg/");
                    imgPath.append("images/blogImg");
                    break;
                default:
                    imgUrl.append("others/");
                    imgPath.append("/others");
            }
            imgUrl.append(imgName);

//            if(fileDao.checkFileName(hashString) == null){
            if(true){

                // 文件存储文件夹
                File imgFolder = new File(imgPath.toString());
                if (!imgFolder.exists()) {
                    imgFolder.mkdirs();
                }

                // 写入文件
                IOUtils.write(file.getBytes(), new FileOutputStream(new File(imgFolder,imgName)));

//                // 记录写入数据库
//                TFile tFile = new TFile();
//                tFile.setUploadMd5(hashString);
//                tFile.setUserId(userId);
//                tFile.setDate(new Date());
//                tFile.setIsAvatar(0);
//                fileDao.insertFileName(tFile);
            }else {
                result.setMessage("文件已存在");
            }

            // 获取不同图片链接后的不同动作
            switch (type){
                case "updateAvatar":
                    userDao.updateAvatar(userId, imgUrl.toString());
                    break;
                case "blogImg":
                    break;
                default:
                    break;
            }

            //返回图片地址
            HashMap map = new HashMap();
            map.put("imgUrl", imgUrl);
            map.put("imgName",hashString);
            result.setCode(200);
            result.setData(map);
            return result;

        }catch (Exception e) {
            e.printStackTrace();
//            result.setResult(ResultStatus.SERVERERR);
            result.setCode(500);
            return result;
        }
    }
}
