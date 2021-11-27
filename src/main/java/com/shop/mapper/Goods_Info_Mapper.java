package com.shop.mapper;

import com.shop.entity.Goods_Info;
import com.shop.entity.Room_Num_Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Goods_Info_Mapper {
    List<Goods_Info> getGoodsList(@Param("start") int start, @Param("page_size") int page_size);
    List<Goods_Info> getGoodsListSelect(@Param("start") int start, @Param("page_size") int page_size, @Param("goods_score") int goods_score);
    Goods_Info getAGoodsInfo(int goods_id);
    List<Room_Num_Info>getRoomNumList(int goods_id);
    String getRoomName(int room_style);
    int getStyle();

    int getRoomPrice(int goods_id, int room_style);

    int getGoodsMonPrice(int goods_id);

    void updateRoomNum(int goods_id, int book_room_num, int room_style);

    int getMaxGoodsId();

    void insertGoodsInfo(Goods_Info goods_info);
}
