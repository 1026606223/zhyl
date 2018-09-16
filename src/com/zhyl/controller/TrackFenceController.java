package com.zhyl.controller;


import com.zhyl.bean.Pos;
import com.zhyl.bean.UserInfo;
import com.zhyl.dao.impl.TrackFenceDao;
import com.zhyl.bean.TrackFence;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
public class TrackFenceController {
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    @RequestMapping(value="/setFenceTrack",method = {RequestMethod.GET,RequestMethod.POST})
    public String setFenceTrack(@RequestBody TrackFence tf){
        //System.out.println(tf.getId());
        TrackFenceDao tfd = new TrackFenceDao();
        try {
            tfd.setTrackFencePoints(tf);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "success";
    }
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    @RequestMapping(value="/getFenceTrack",method = {RequestMethod.GET,RequestMethod.POST})
    public TrackFence getFenceTrack(HttpServletRequest request){
        String ID = "";
        HttpSession session = request.getSession();
        UserInfo uifo = (UserInfo)session.getAttribute("uifo");
        ID = uifo.getID();
        TrackFenceDao tfd = new TrackFenceDao();
        List<Pos> tfp = tfd.getTrackFencePoints(ID);
        if(!tfp.isEmpty()){
            TrackFence tf= new TrackFence();
            tf.setId(ID);
            Pos[] tmp = new Pos[tfp.size()];
            tfp.toArray(tmp);
            tf.setFenceTrack(tmp);
            return tf;
        }
        else{
            return null;
        }
    }
}
