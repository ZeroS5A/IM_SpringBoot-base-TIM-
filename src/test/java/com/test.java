package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Autowired
    DataSource dataSource;

    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void hello() throws SQLException {
        logger.trace("日志测试");
        logger.debug("debug信息");
        dataSource.getClass();
        Connection connection =dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    public void test1(){
        System.out.println(
                "SELECT\n" +
                        "	t_blog.blogId,\n" +
                        "	t_blog.userName,\n" +
                        "	t_blog.classId,\n" +
                        "	t_blog.title,\n" +
                        "	t_blog.postTime,\n" +
                        "	t_blog.content,\n" +
                        "	t_blog.imageList,\n" +
                        "	t_user.avatarUrl,\n" +
                        "	a.likeNum,\n" +
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
                        "ORDER BY\n" +
                        "t_blog.postTime\n" +
                        "DESC"
        );
    }
}
