package com.zhyl.test;

import com.zhyl.bean.UserInfo;
import com.zhyl.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.*;
import java.util.List;


public class test {
    @Test
    public void main() throws ClassNotFoundException {
        //Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = null;
        PreparedStatement ps = null;
        try{
        //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/com.zhyl.test.test", "root", "5758895");
        //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/com.zhyl.test.test?useSSL=false&serverTimezone=Hongkong&useUnicode=true&characterEncoding=utf-8&user=root&password=5758895");
        //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/com.zhyl.test.test?serverTimezone=Hongkong&user=root&password=5758895");
        conn = DriverManager.getConnection("jdbc:mysql://120.79.209.190:3306/zhyl?user=root&password=123456&serverTimezone=Hongkong&characterEncoding=utf-8");


        ps = conn.prepareStatement("select * from user_health");

        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1)+"  "+rs.getString(2));
        }
        rs.close();
        ps.close();
        conn.close();
    }
    catch (SQLException e){
        e.printStackTrace();
    }
}
    @Test
    public void tst(){
        System.out.println("aaaa");
    }

    @Test
    public void tst2(){
       try{
        Connection conn = C3P0Util.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from user_health");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1)+"  "+rs.getString(2));
        }
        C3P0Util.release(conn,ps,rs);
    }catch(Exception e){
           e.printStackTrace();
       }
    }
    @Test
    public void tst3() throws SQLException {
        //创建一个QueryRunner对象
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        //执行查询语句，并将返回的结果封装到指定bean中,qr.query的第二个参数写new BeanListHandler<User>(User.class)
        List<UserInfo> lst = qr.query("select * from user_information", new BeanListHandler<UserInfo>(UserInfo.class));
        for(UserInfo u : lst){
            System.out.println(u);
        }
    }
}


