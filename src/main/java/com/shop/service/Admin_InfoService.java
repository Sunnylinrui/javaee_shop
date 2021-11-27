package com.shop.service;

import com.shop.entity.*;
import com.shop.mapper.Admin_Info_Mapper;
import com.shop.mapper.Goods_Info_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Admin_InfoService {
    @Autowired
    private Admin_Info_Mapper admin_info_mapper;
    @Autowired
    private Goods_Info_Mapper goods_info_mapper;
    public  void delete_user(int user_id) {
        admin_info_mapper.delete_user(user_id);
    }

    public Admin_Info checkLogin(int admin_id, String admin_pwd) {
        return admin_info_mapper.getAAdmin_Info(admin_id,admin_pwd);
    }

    //获取管理信息
    public Admin_Info getAdminInfo(int admin_id) {
        return admin_info_mapper.getAdmin_Info(admin_id);
    }

    //获取所有商品信息
    public List<Goods_Info> getAllGoodsInfo() {
        return admin_info_mapper.getAllAdmin();
    }

    //获取所有订单信息
    public List<Order_Info> getAllorderInfo(String field_name, String field_value, int start_page, int page_size) {
        return admin_info_mapper.getAllorderInfo(field_name,field_value,start_page,page_size);
    }

    //获取所有用户信息
    public List<User_Info> getAllUserInfo(String field_name, String field_value, int start_page, int page_size) {
        return admin_info_mapper.getAllUserInfo(field_name,field_value,start_page,page_size);
    }

    public List<User_Info> getAllUser() {
        return admin_info_mapper.getAllUser();
    }
    public List<User_List> getAllUserList(){
        List<User_Info> user_info_lists = admin_info_mapper.getAllUser();
        int count = user_info_lists.size();
        List<User_List> userLists = new ArrayList<>();
        for (int i=0;i<count;i++){
            User_List user_list=new User_List();
            user_list.setUser_id(user_info_lists.get(i).getUser_id());
            user_list.setUser_name(user_info_lists.get(i).getUser_name());
            user_list.setUser_pwd(user_info_lists.get(i).getUser_pwd());
            user_list.setUser_phone(user_info_lists.get(i).getUser_phone());
            user_list.setUser_sfz(user_info_lists.get(i).getUser_sfz());
            user_list.setUser_money(user_info_lists.get(i).getUser_money());
            if(user_info_lists.get(i).getVip_style()==0){
                user_list.setVip_style("普通会员");
            }if(user_info_lists.get(i).getVip_style()==1){
                user_list.setVip_style("白银会员");
            }if(user_info_lists.get(i).getVip_style()==2){
                user_list.setVip_style("黄金会员");
            }if(user_info_lists.get(i).getVip_style()==3){
                user_list.setVip_style("钻石会员");
            }
            userLists.add(user_list);
        }
        return userLists;
    }
    //得到Vip列表
    public List<Vip_Info> getVipList() {
        return admin_info_mapper.getVipList();
    }
    //更新用户信息
    public void updateUser(User_Info user_info) {
        admin_info_mapper.updateUser(user_info);
    }
    //得到所有进行中订单的列表
    public List<Order_Info> getAllorderIngInfo(String field_name, String field_value, int start_page, int page_size) {
        return admin_info_mapper.getAllorderIngInfo(field_name,field_value,start_page,page_size);
    }
    //得到所有退订订单的列表
    public List<Order_Info> getAllorderTdInfo(String field_name, String field_value, int start_page, int page_size) {
        return admin_info_mapper.getAllorderTdInfo(field_name,field_value,start_page,page_size);
    }
    //删除订单
    public void deleteOrder(int order_id) {
        admin_info_mapper.delete_order(order_id);
    }
    //更改订单状态
    public void setOrderState(int order_id) {
        admin_info_mapper.setOrderState(order_id);
    }
    //增加商品营业额
    public void addGoodsMoney(int goods_id, double order_price) {
        admin_info_mapper.addGoodsMoney(goods_id,order_price);
    }
    //得到商品类型
    public List<Room_style_Info> getRoomStyle() {
        return admin_info_mapper.getRoomStyle();
    }
    //更新商品类型
    public void updateRoom(int goods_id, int room_style, int room_num, int room_price) {
        admin_info_mapper.updateRoom(goods_id,room_style,room_num,room_price);
    }

    //得到商品各类型信息
    public List<Book_room_info> getRoomInfo(int goods_id) {
        List<Book_room_info> book_room_infoList = new ArrayList<>();
        List<Room_Num_Info> room_num_infoList=admin_info_mapper.getRoomInfo(goods_id);
        int count = room_num_infoList.size();
        for (int i=0;i<count;i++){
            Book_room_info book_room_info = new Book_room_info();
            book_room_info.setRoom_style(room_num_infoList.get(i).getRoom_style());
            book_room_info.setRoom_name(goods_info_mapper.getRoomName(room_num_infoList.get(i).getRoom_style()));
            book_room_info.setRoom_num(room_num_infoList.get(i).getRoom_num());
            book_room_info.setRoom_price(room_num_infoList.get(i).getRoom_price());
            book_room_infoList.add(book_room_info);
        }
        return book_room_infoList;
    }

    public void insertRoom(Room_Num_Info room_num_info) {
        admin_info_mapper.insertRoom(room_num_info);
    }

    public List<Title_Info> getAllTitleInfo() {
        return admin_info_mapper.getAllTitle();
    }

    public Title_Info getTitleInfo(int title_id) {
        return admin_info_mapper.getTitle_Info(title_id);
    }

    public void updateTitle(Title_Info title_info) {
        admin_info_mapper.updateTitle(title_info);
    }

    public int getMaxTitleid() {
        return admin_info_mapper.getMaxTitleId();
    }

    public void insertTitle(Title_Info title_info) {
        admin_info_mapper.insertTitleInfo(title_info);
    }

    public  void delete_title(int title_id) {
        admin_info_mapper.delete_title(title_id);
    }
}
