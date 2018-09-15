package com.zhyl.dao;

import com.zhyl.bean.UserInfo;

public interface UserInfoDao {
    /**
     * 添加用户信息
     * @param user
     * @throws Exception
     */
    public void addUser(UserInfo user) throws Exception;

    /**
     * 查询ID为id的用户信息
     * @param id
     * @return
     * @throws Exception
     */
    public UserInfo searchUser(String id) throws Exception;
}
