package com.shop.mapper;

import com.shop.entity.Money_info;
import com.shop.entity.Money_info;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Money_Info_Mapper {
     void insertMoney(double sum);
     List<Money_info> getMoney();
     void deleteMoney();
}
