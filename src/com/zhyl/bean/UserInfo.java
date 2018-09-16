package com.zhyl.bean;

import org.junit.Test;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    private String ID;
    private String password;
    private String realName;
    private Date birthday;
    private String gender;
    private String phone;
    private String Equip_ID;

    public String getEquip_ID() {
        return Equip_ID;
    }

    public void setEquip_ID(String equip_ID) {
        Equip_ID = equip_ID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRealName() {

        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "ID='" + ID + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

