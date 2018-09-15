package com.zhyl.service;

import com.zhyl.bean.UserInfo;

public interface Service {
    /**
     * 注册服务
     * @param user
     * @throws Exception
     */
    public void register(UserInfo user) throws  Exception;

    /**
     * 查询重复id服务，若重名则返回true
     * @param id
     * @return
     * @throws Exception
     */
    public boolean checkRepeat(String id) throws Exception;

    public UserInfo loginVerify(String id, String pwd) throws  Exception;
}
