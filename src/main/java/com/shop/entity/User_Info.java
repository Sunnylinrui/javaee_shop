package com.shop.entity;

public class User_Info {
    private int user_id;
    private String user_pwd;
    private String user_name;
    private String user_phone;
    private String user_sfz;
    private int vip_style;
    private double user_money;
    private String user_discuss;

    public String getUser_discuss() {
        return user_discuss;
    }

    public void setUser_discuss(String user_discuss) {
        this.user_discuss = user_discuss;
    }

    public int getUser_id() {
        return user_id;
    }

    public double getUser_money() { return user_money; }

    public void setUser_money(double user_money) {
        this.user_money = user_money;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_sfz() {
        return user_sfz;
    }

    public void setUser_sfz(String user_sfz) {
        this.user_sfz = user_sfz;
    }

    public int getVip_style() {
        return vip_style;
    }

    public void setVip_style(int vip_style) {
        this.vip_style = vip_style;
    }
}

