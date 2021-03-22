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
                "Select\n" +
                        "t_user.userTimId\n" +
                        "From \n" +
                        "t_user\n" +
                        "Where \n" +
                        "userName=#{0}\n" +
                        "AND \n" +
                        "password=#{1}"
        );
    }
}
