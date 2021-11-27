package com.shop.mapper;

import com.shop.entity.Order_Info;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Order_Info_Mapper {
    int getMaxId();

    void insert(Order_Info order_info);

    List<Order_Info> getUserOrder(int user_id);

    void addMoney(int user_id, double money);

    void deleteOrder(int order_id);

    Order_Info getAOrder(int order_id);

    void setTD(int order_id);
}
