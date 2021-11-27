package com.shop.service;

import com.shop.entity.Car_Info;
import com.shop.mapper.Car_Info_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Car_InfoService {
    @Resource
    private Car_Info_Mapper car_info_mapper;
    public List<Car_Info> getCarListByCondition(String field_name, String field_value,
                                                int start_page, int page_size){
        List<Car_Info>car_infoList = null;
        int start = (start_page-1)*page_size;
        if(start<0)start=0;
        car_infoList = car_info_mapper.getCar_InfoList(field_name,
                field_value, start, page_size);
        return car_infoList;
    }
    public int deleteGoods_Info(int goods_id){
        int flag = 0;
        flag = car_info_mapper.deleteCar_Info(goods_id);
        return  flag;
    }
    public int AddNum(int goods_id){
        int flag = 0;
        flag = car_info_mapper.AddNum(goods_id);
        return flag;
    }
    public int DeleteNum(int goods_id){
        int flag = 0;
        flag = car_info_mapper.DeleteNum(goods_id);
        return flag;
    }

    public void deleteAll(){
        car_info_mapper.deleteAll();
    }

    public List<Car_Info>  getAllList(){
        List<Car_Info> car_infos = car_info_mapper.getAllList();
        return car_infos;
    }

}

