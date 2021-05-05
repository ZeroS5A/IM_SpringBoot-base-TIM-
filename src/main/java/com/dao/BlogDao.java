package com.dao;

import com.bean.TBlog;
import com.bean.TBloglike;
import com.bean.TComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogDao {
    @Select("SELECT\n" +
            "	t_blog.blogId,\n" +
            "	t_blog.userName,\n" +
            "	t_blog.classId,\n" +
            "	t_blog.title,\n" +
            "	t_blog.postTime,\n" +
            "	t_blog.content,\n" +
            "	t_blog.imageList,\n" +
            "	t_user.avatarUrl,\n" +
            "	a.likeNum,\n" +
            "	b.isLiked,\n" +
            "	t_user.userTimId\n" +
            "FROM\n" +
            "	t_blog\n" +
            "LEFT JOIN\n" +
            "	t_user\n" +
            "ON\n" +
            "	t_user.userName = t_blog.userName\n" +
            "LEFT JOIN\n" +
            "	(SELECT\n" +
            "		count(*) as likeNum,\n" +
            "		blogId\n" +
            "		FROM\n" +
            "		t_bloglike\n" +
            "		GROUP BY\n" +
            "		t_bloglike.blogId\n" +
            "		) a\n" +
            "ON\n" +
            "a.blogId=t_blog.blogId\n" +
            "LEFT JOIN\n" +
            "	(SELECT\n" +
            "		count(*) as isLiked,\n" +
            "		blogId\n" +
            "		FROM\n" +
            "		t_bloglike\n" +
            "		WHERE\n" +
            "		t_bloglike.userName = #{1}\n" +
            "		GROUP BY\n" +
            "		t_bloglike.blogId\n" +
            "		) b\n" +
            "ON\n" +
            "b.blogId=t_blog.blogId\n" +
            "ORDER BY\n" +
            "t_blog.postTime\n" +
            "DESC")
    List<TBlog> getBlogList(String userName);

    @Select("SELECT\n" +
            "	t_blog.blogId,\n" +
            "	t_blog.userName,\n" +
            "	t_blog.classId,\n" +
            "	t_blog.title,\n" +
            "	t_blog.postTime,\n" +
            "	t_blog.content,\n" +
            "	t_blog.imageList,\n" +
            "	t_user.avatarUrl,\n" +
            "	a.likeNum,\n" +
            "	b.isLiked,\n" +
            "	t_user.userTimId\n" +
            "FROM\n" +
            "	t_blog\n" +
            "LEFT JOIN\n" +
            "	t_user\n" +
            "ON\n" +
            "	t_user.userName = t_blog.userName\n" +
            "LEFT JOIN\n" +
            "	(SELECT\n" +
            "		count(*) as likeNum,\n" +
            "		blogId\n" +
            "		FROM\n" +
            "		t_bloglike\n" +
            "		GROUP BY\n" +
            "		t_bloglike.blogId\n" +
            "		) a\n" +
            "ON\n" +
            "a.blogId=t_blog.blogId\n" +
            "LEFT JOIN\n" +
            "	(SELECT\n" +
            "		count(*) as isLiked,\n" +
            "		blogId\n" +
            "		FROM\n" +
            "		t_bloglike\n" +
            "		WHERE\n" +
            "		t_bloglike.userName = #{userName}\n" +
            "		GROUP BY\n" +
            "		t_bloglike.blogId\n" +
            "		) b\n" +
            "ON\n" +
            "b.blogId=t_blog.blogId\n" +
            "WHERE\n" +
            "	t_blog.userName = #{userName}\n" +
            "ORDER BY\n" +
            "t_blog.postTime\n" +
            "DESC")
    List<TBlog> getBlogListById(String userName);

    @Insert("INSERT INTO \n" +
            "	t_blog (userName,classId,title,postTime,content,imageList) \n" +
            "VALUES \n" +
            "	(#{userName},#{classId},#{title},#{postTime},#{content},#{imageList})")
    @Options(useGeneratedKeys = true, keyProperty = "blogId", keyColumn = "blogId")
    public Integer insertBlog(TBlog tBlog);

    @Select("SELECT\n" +
            "	*\n" +
            "FROM\n" +
            "	t_comment\n" +
            "WHERE\n" +
            "	blogId = #{blogId}")
    public List<TComment> getCommentList(String blogId);

    @Insert("INSERT INTO\n" +
            "	t_comment(userName,blogId,commentTime,content)\n" +
            "VALUES\n" +
            "	(#{userName},#{blogId},#{commentTime},#{content})")
    @Options(useGeneratedKeys = true, keyProperty = "commentId", keyColumn = "commentId")
    Integer insertComment(TComment tComment);

    @Delete("DELETE FROM\n" +
            "	t_comment\n" +
            "WHERE\n" +
            "	commentId = #{commentId}")
    Integer deleteCommentById(Integer commentId);

    @Delete("DELETE FROM\n" +
            "	t_blog\n" +
            "WHERE\n" +
            "	blogId = #{blogId}")
    Integer deleteBlogById(Integer blogId);

    @Insert(
            "INSERT INTO\n" +
                    "	t_bloglike(blogId,userName)\n" +
                    "VALUES\n" +
                    "	(#{0},#{1})"
    )
    Integer insertLike(Integer blogId,String userName);

    @Delete(
            "DELETE FROM\n" +
                    "	t_bloglike\n" +
                    "WHERE\n" +
                    "	t_bloglike.blogId=#{0}\n"+
                    "   AND\n"+
                    "   t_bloglike.userName=#{1}"
    )
    Integer deleteLike(Integer blogId,String userName);
}
