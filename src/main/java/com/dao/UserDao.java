package com.dao;

import com.bean.Student;
import com.bean.TUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    @Select("select * from t_student")
    public List<Student> selAllStudent();

    @Select(
        "Select\n" +
            "userName, userTimId, userNickName, email, avatarUrl, role\n" +
                "From \n" +
            "t_user\n" +
            "Where \n" +
            "userName=#{0}\n" +
            "AND \n" +
            "userPassword=#{1}"
    )
    TUser userLogin(String userName, String password);

    @Select(
        "SELECT \n" +
            "	t_relation.relationUser2 \n" +
            "FROM \n" +
            "	`t_relation`\n" +
            "WHERE\n" +
            "	t_relation.relationUser1 = #{userID}"
    )
    List<String> gerUserRelation(String userID);

    @Delete(
            "DELETE FROM\n" +
                    "	t_relation\n" +
                    "WHERE\n" +
                    "	t_relation.relationUser1=#{0}\n" +
                    "   AND\n"+
                    "	t_relation.relationUser2=#{1}"
    )
    int deleteRelationShip(String userId1, String userId2);

    @Select(
            "SELECT\n" +
                    "count(t_user.userName)\n" +
                    "FROM\n" +
                    "t_user\n" +
                    "WHERE\n" +
                    "t_user.email=#{0}"
    )
    //检查邮箱
    public int checkEmail(String email);

    @Update(
            "UPDATE\n" +
                    "	t_user u\n" +
                    "	SET\n" +
                    "	u.email=#{1}\n" +
                    "	WHERE\n" +
                    "	u.userName=#{0}"
    )
    //更新邮箱
    public int updateEmail(String userId,String email);

    @Update(
            "UPDATE\n" +
                    "	t_user u\n" +
                    "	SET\n" +
                    "	u.avatarUrl=#{1}\n" +
                    "	WHERE\n" +
                    "	u.userName=#{0}"
    )
    //更新头像
    public int updateAvatar(String userId,String avatar);

    @Update(
            "UPDATE\n" +
                    "	t_user u\n" +
                    "	SET\n" +
                    "	u.userPassword=#{1}\n" +
                    "	WHERE\n" +
                    "	u.userName=#{0}"
    )
    //更新密码
    public int updatePassWd(String userId,String passWd);

    @Select(
        "SELECT\n" +
            " t_user.userTimId\n" +
            "FROM\n" +
            " t_user\n" +
            "WHERE\n" +
            " t_user.userName = #{0}"
    )
    String getUserTimID(String userName);

    @Insert("INSERT INTO t_relation(relationUser1,relationUser2) VAlUES(#{0},#{1})")
    int addUserRelation(String user1, String user2);

    @Select("SELECT \n" +
            "	userTimId, userNickName, email, avatarUrl\n" +
            "FROM `t_user` \n" +
            "WHERE userName = #{0}")
    Map<String,String> getUserData(String userID);

    @Select(
        "SELECT\n" +
            "t_user.userName\n" +
            "FROM\n" +
            "t_user\n" +
            "WHERE\n" +
            "t_user.userName=#{0}"
    )
    //检查用户名
    public String checkUserName(String userName);

    @Insert(
        "INSERT INTO\n" +
            "	t_user(userName, userTimId, userPassword, email)\n" +
            "VALUES\n" +
            "	(#{0},#{0},#{1},#{2})"
    )
    //用户注册
    public Integer userRegister(String userName, String password, String email);
}
