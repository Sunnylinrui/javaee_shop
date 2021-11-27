package com.shop.service;

import com.shop.entity.Order_Info;
import com.shop.mapper.Order_Info_Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Order_InfoService {
    @Resource
    private Order_Info_Mapper order_info_mapper;
    public int getMaxId() {
        return order_info_mapper.getMaxId();
    }

    public void insert(Order_Info order_info) {
        order_info_mapper.insert(order_info);
    }

    public List<Order_Info> getUserOrder(int user_id) {
        return order_info_mapper.getUserOrder(user_id);
    }
    public void addMoney(int user_id,double money){
        order_info_mapper.addMoney(user_id,money);
    };
    public void deleteOrder(int order_id){
         order_info_mapper.deleteOrder(order_id);
    };

    public Order_Info getAUserOrder(int order_id) {
        return order_info_mapper.getAOrder(order_id);
    }

    public void setTD(int order_id) {
        order_info_mapper.setTD(order_id);
    }
}
