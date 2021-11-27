package com.shop.service;

import com.shop.entity.Money_info;
import com.shop.mapper.Money_Info_Mapper;
import com.shop.entity.Money_info;
import com.shop.mapper.Money_Info_Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Money_InfoService {
    @Autowired
    private Money_Info_Mapper money_info_mapper;
    public List<Money_info> getMoney(){
        List<Money_info> money_infos = money_info_mapper.getMoney();
        return  money_infos;
    }
    public void deleteMoney(){
        money_info_mapper.deleteMoney();
    }
    public void insertMoney(double sum){
        money_info_mapper.insertMoney(sum);
    }
}
