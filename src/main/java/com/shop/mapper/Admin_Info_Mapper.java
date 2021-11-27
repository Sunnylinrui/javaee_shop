package com.shop.mapper;

import com.shop.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Admin_Info_Mapper {
    Admin_Info getAAdmin_Info(@Param("0") int admin_id,
                              @Param("1") String admin_pwd);

    Admin_Info getAdmin_Info(int admin_id);

    List<Goods_Info> getAllAdmin();
    List<Order_Info> getAllorderInfo(@Param("field_name") String field_name,
                                     @Param("field_value") String field_value,
                                     @Param("start") int start,
                                     @Param("page_size") int page_size);

    List<User_Info> getAllUserInfo(@Param("field_name") String field_name, @Param("field_value") String field_value,
                                   @Param("start") int start, @Param("page_size") int page_size);

    List<User_Info> getAllUser();

    void delete_user(int user_id);

    List<Vip_Info> getVipList();

    void updateUser(User_Info user_info);

    List<Order_Info> getAllorderIngInfo(@Param("field_name") String field_name,
                                        @Param("field_value") String field_value,
                                        @Param("start") int start,
                                        @Param("page_size") int page_size);

    List<Order_Info> getAllorderTdInfo(@Param("field_name") String field_name,
                                       @Param("field_value") String field_value,
                                       @Param("start") int start,
                                       @Param("page_size") int page_size);

    void delete_order(int order_id);

    void setOrderState(int order_id);

    void addGoodsMoney(int goods_id, double order_price);

    List<Room_style_Info> getRoomStyle();

    void updateRoom(int goods_id, int room_style, int room_num, int room_price);

    List<Room_Num_Info> getRoomInfo(int goods_id);

    void insertRoom(Room_Num_Info room_num_info);

    List<Title_Info> getAllTitle();

    Title_Info getTitle_Info(int title_id);

    void updateTitle(Title_Info title_info);

    int getMaxTitleId();

    void insertTitleInfo(Title_Info title_info);

    void delete_title(int title_id);
}

