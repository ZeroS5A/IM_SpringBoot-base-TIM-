package com.server;

import com.commom.Result;
import org.springframework.web.multipart.MultipartFile;

public interface UploadServer {
    public Result uploadImage(String userId, MultipartFile file, String type);
}
