package com.shop.service;

import com.shop.entity.Car_Info;
import com.shop.entity.Goods_Info;
import com.shop.entity.Room_Num_Info;
import com.shop.mapper.Car_Info_Mapper;
import com.shop.mapper.Goods_Info_Mapper;
import com.shop.mapper.User_Info_Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Goods_InfoService {
    @Resource
    private Goods_Info_Mapper goods_info_mapper;
    @Resource
    private User_Info_Mapper user_info_mapper;
    @Resource
    private Car_Info_Mapper car_info_mapper;
    @RequestMapping("show_goods_list")
    public List<Goods_Info> getGoodsList(int start_page, int page_size, int goods_score) {
        int start = (start_page - 1) * page_size;
        if (start < 0) {
            start = 0;
        }
        if (goods_score==6){
            return goods_info_mapper.getGoodsList(start, page_size);
        }
        else {
            return goods_info_mapper.getGoodsListSelect(start, page_size,goods_score);
        }
    }
    public Goods_Info getGoodsInfo(int goods_id){return goods_info_mapper.getAGoodsInfo(goods_id);}
    public List<Room_Num_Info> getRoomNumList(int goods_id){
        return goods_info_mapper.getRoomNumList(goods_id);
    }

    public int getRoomStyle() {
        return goods_info_mapper.getStyle();
    }
    public String getRoomName(int room_style){
        return goods_info_mapper.getRoomName(room_style);
    }
    public int getRoomPrice(int goods_id, int room_style) {
        return goods_info_mapper.getRoomPrice(goods_id,room_style);
    }

    public int getGoodsMonPrice(int goods_id) {
        return goods_info_mapper.getGoodsMonPrice(goods_id);
    }

    public void updateRoomInfo(int goods_id, int book_room_num, int room_style) {
        goods_info_mapper.updateRoomNum(goods_id,book_room_num,room_style);
    }

    public void updateUserInfo(int user_id, double price) {
        user_info_mapper.updateUserPrice(user_id,price);
    }

    public int getMaxGoodsid() {
        return goods_info_mapper.getMaxGoodsId();
    }

    public void insertGoods(Goods_Info goods_info) {
        goods_info_mapper.insertGoodsInfo(goods_info);
    }

    //购物车里
    public void insertACar(Car_Info car_info){
        car_info_mapper.insertACar(car_info);
    }

}
