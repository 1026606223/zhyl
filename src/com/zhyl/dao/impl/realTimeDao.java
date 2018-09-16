package com.zhyl.dao.impl;

import com.zhyl.bean.position;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.zhyl.utils.database.conn;

@Repository
public class realTimeDao {
    Connection connection = null;

    /**
     * 输入实时位置，经纬度
     * 需要获取老人ID，还没写
     * 位置记录ID自增
     * @param LAT
     * @param LON
     * @param ID
     */
    public void addRealPosition(String LAT, String LON, String ID) {
        PreparedStatement stmt = null;
        try {
            connection = conn();
            String sql;
            sql = "insert into user_realposition (position_LON,position_LAT,position_Time,ID) values(?,?,?,?)";
            stmt = connection.prepareStatement(sql);
            //获取时间
            String time;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            // new Date()为获取当前系统时间
            time = df.format(new Date());
            stmt.setString(1, LON);
            stmt.setString(2, LAT);
            stmt.setString(3,time);
            stmt.setString(4,ID);
            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("Cannot connect to database server,Exception:"
                    + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * 获取老人轨迹
     * 需要获取老人ID，还没写
     * @param id
     * @return
     */
    public List<position> getRTPosition(String id){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<position> positions = new ArrayList<>();
        try{
            connection = conn();
            String sql;
            sql = "select * from user_realposition where ID=?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,id);
            rs = stmt.executeQuery();
            while (rs.next()){
                position P = new position();
                P.setLAT(rs.getString("position_LAT"));
                P.setLON(rs.getString("position_LON"));
                P.setTime(rs.getString("position_Time"));
                P.setID(rs.getString("ID"));
                positions.add(P);
            }
            stmt.close();
            connection.close();

        } catch (Exception e) {
            System.err.println("Cannot connect to database server,Exception:"
                    + e.getMessage());
        }finally {
            if(connection != null){
                try {
                    connection.close();
                    connection = null;
                } catch (Exception e) {
                }
            }
        }
        return positions;
    }

}
