package com.shop.service;

import com.shop.entity.Goods_Info;
import com.shop.entity.User_Info;
import com.shop.mapper.User_Info_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User_InfoService {
    @Autowired
    private User_Info_Mapper user_info_mapper;
    public User_Info checkLogin(int user_id, String user_pwd){
        return user_info_mapper.getAUser_Info(user_id,user_pwd);
    }
    public String getAUser_name(int user_id){
        return user_info_mapper.getName(user_id);
    }
    public int getAUser_vip(int user_id){
        return user_info_mapper.getvipstyle(user_id);
    }
    public String getvip(int user_vip){
        return user_info_mapper.getvip(user_vip);
    }
    public void register(User_Info user_info){user_info_mapper.register(user_info);}
    public User_Info getUserInfo(int user_id){return user_info_mapper.getAUser(user_id);}
    public void deposit(int money,int user_id){
        if (money>0){ user_info_mapper.deposit(money,user_id); }
    }
    public int getVipValue(int vip_style){return user_info_mapper.getVipValue(vip_style);}
    public void buy_vip(int money,int user_id,int vip_style) {
        user_info_mapper.buy_vip(money,user_id,vip_style);
    }

    public void updateuserinfo(int user_id, String user_name, String user_phone, String user_sfz) {
        user_info_mapper.updatauserinfo(user_id,user_name,user_phone,user_sfz);
    }

    public double getDicount(int vip_style) {
        return user_info_mapper.getDiscount(vip_style);
    }

    //添加评论
    public void insertDiscuss(User_Info user_info) {
        user_info_mapper.insertDiscussInfo(user_info);
    }
}
