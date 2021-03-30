package com.dao;

import com.bean.TFile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileDao {
    @Insert(
            "INSERT\n" +
                    "	t_file\n" +
                    "	SET\n" +
                    "	t_file.fileMd5=#{fileMd5},\n" +
                    "	t_file.uploadMd5=#{uploadMd5},\n" +
                    "	t_file.date=#{date},\n" +
                    "	t_file.userId=#{userId},\n" +
                    "	t_file.blogId=#{blogId},\n" +
                    "	t_file.isAvatar=#{isAvatar}"
    )
    public int insertFileName(TFile tFile);


    @Select("Select fileId From t_file Where t_file.uploadMd5=#{0}")
    public TFile checkFileName(String fileMd5);

    @Delete("Delete From t_file Where t_file.blogId=#{0}")
    public int deleteFileByBlogId(Integer blogId);

}
