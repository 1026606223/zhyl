package com.zhyl.service.impl;

import com.zhyl.bean.UserInfo;
import com.zhyl.dao.impl.UserInfoDaoImpl;
import com.zhyl.service.Service;
import com.zhyl.dao.UserInfoDao;
public class ServiceImpl implements Service {
    @Override
    public void register(UserInfo user) throws Exception {
        UserInfoDao userInfoDao = new UserInfoDaoImpl();
        userInfoDao.addUser(user);
    }

    @Override
    public boolean checkRepeat(String id) throws Exception {
        UserInfoDao uid = new UserInfoDaoImpl();
        if(uid.searchUser(id) != null){
            return true;
        }
        else return false;
    }

    @Override
    public UserInfo loginVerify(String id, String pwd) throws Exception {
        UserInfoDao uifd = new UserInfoDaoImpl();
        UserInfo uifo = uifd.searchUser(id);
        if((uifo!=null)&&(pwd.equals(uifo.getPassword()))){
            return uifo;
        }
        else return null;
    }
}
