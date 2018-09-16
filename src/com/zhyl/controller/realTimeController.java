package com.zhyl.controller;

import com.zhyl.bean.UserInfo;
import com.zhyl.dao.impl.realTimeDao;
import com.zhyl.bean.position;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class realTimeController {
    /**
     * 添加实时位置
     * @param LAT
     * @param LON
     * @param ID
     * @param response
     */
    @ResponseBody
    @RequestMapping(value="/addRTPosition",method={RequestMethod.POST,RequestMethod.GET})
    public void addUser(
            @RequestParam(value = "LAT", required = true) String LAT,
            @RequestParam(value = "LON", required = true) String LON,
            @RequestParam(value = "ID", required = true) String ID,
            HttpServletResponse response, HttpServletRequest request)
    {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");// 设置跨域请求
        realTimeDao dao = new realTimeDao();
        HttpSession session = request.getSession();
        UserInfo uifo = (UserInfo)(session.getAttribute("uifo"));
        ID = uifo.getID();
        dao.addRealPosition(LAT,LON,ID);

    }


    /**
     * 返回老人轨迹
     * @param response
     * @param ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getPosition",method={RequestMethod.POST,RequestMethod.GET})
    public Map<String,List> getPosition(HttpServletResponse response,
                                        @RequestParam(value = "ID", required = true)String ID)
    {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");// 设置跨域请求
        realTimeDao dao = new realTimeDao();
        List<position> list = dao.getRTPosition(ID);
        Map<String,List> map = new HashMap<>();
        map.put("position",list);
        return map;
    }

}
