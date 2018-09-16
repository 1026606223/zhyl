package com.zhyl.dao.impl;

import com.zhyl.bean.Pos;
import com.zhyl.bean.TrackFence;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zhyl.utils.database.conn;

@Repository
public class TrackFenceDao {
    Connection connection = null;

    /**
     * 从数据库获得电子围栏
     * @param ID
     * @return
     */
    public List<Pos> getTrackFencePoints(String ID){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pos> tfp = new ArrayList<Pos>();
        try{
            connection = conn();
            String sql;
            sql = "select AsText(track_limit) from user_limitTrackPoints where ID=?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,ID);
            rs = stmt.executeQuery();
            while (rs.next()){
                Object points = null;
                points = rs.getObject(1);
                //System.out.println(points.toString());
                Pattern p =Pattern.compile("([0-9]+[.]?[0-9]+) ([0-9]+[.]?[0-9]+)");
                Matcher m =p.matcher((String)points);
                while(m.find()){
                    Pos pos = new Pos();
                    pos.setLat(m.group(1));
                    pos.setLon(m.group(2));
                    tfp.add(pos);
                }
            }
            stmt.close();
            connection.close();
            return tfp;
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
            return tfp;
        }
    }

    /**
     * 用以判断数据库是否已经有围栏数据
     * @param ID
     * @return
     * @throws SQLException
     */
    public boolean checkFenceExists(String ID) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        connection = conn();
        String sql = "select ID from user_limitTrackPoints where ID = ?";
        stmt = connection.prepareStatement(sql);
        stmt.setString(1,ID);
        rs = stmt.executeQuery();
        if(rs.next()) return true;
        else return false;
    }




    /**
     * 设定或者重置电子围栏
     * @param tf
     * @return
     */
    public String setTrackFencePoints(TrackFence tf) throws SQLException {
        PreparedStatement stmt = null;
        connection = conn();
        String sql;
        String pointString = "";
        for(int i=0;i<tf.getFenceTrack().length;i++){
            if(i!=tf.getFenceTrack().length-1){
                pointString+=tf.getFenceTrack()[i].getLat()+" "+tf.getFenceTrack()[i].getLon()+",";
            }
            else pointString+=tf.getFenceTrack()[i].getLat()+" "+tf.getFenceTrack()[i].getLon();
        }
        if(checkFenceExists(tf.getId())){
            sql = "update user_limitTrackPoints set track_limit=GeomFromText('MULTIPOINT("+pointString+")') where ID=?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,tf.getId());
            stmt.executeUpdate();
        }
        else{
            sql = "insert into user_limitTrackPoints values(?,GeomFromText('MULTIPOINT("+pointString+")'))";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,tf.getId());
            stmt.executeUpdate();
        }
        return "success";
    }
}
