package com.shop.mapper;

import com.shop.entity.Goods_Info;
import com.shop.entity.User_Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface User_Info_Mapper {
    User_Info getAUser_Info(@Param("0") int user_id,
                            @Param("1") String user_pwd);
    String getName(int user_id);
    String  getvip(int user_vip);
    int getvipstyle(int user_id);
    void register(User_Info user_info);
    User_Info getAUser(int user_id);
    void deposit(int money, int user_id);
    int getVipValue(int vip_style);
    void buy_vip(int money, int user_id, int vip_style);

    void updatauserinfo(int user_id, String user_name, String user_phone, String user_sfz);

    void updateUserPrice(int user_id, double price);

    double getDiscount(int vip_style);

    void insertDiscussInfo(User_Info user_info);
}

