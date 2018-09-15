package com.zhyl.dao.impl;

import com.zhyl.bean.UserInfo;
import com.zhyl.dao.UserInfoDao;
import com.zhyl.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class UserInfoDaoImpl implements UserInfoDao {
    @Override
    public void addUser(UserInfo user) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("insert user_information VALUES(?,?,?,?,?,?)",user.getID(),user.getPassword(),user.getRealName(),user.getBirthday(),user.getGender(),user.getPhone());
    }

    @Override
    public UserInfo searchUser(String id) throws Exception {
        UserInfo uinfo = null;
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        uinfo = qr.query("select * from user_information where ID = ?", new BeanHandler<UserInfo>(UserInfo.class), id);
        return uinfo;
    }


}
