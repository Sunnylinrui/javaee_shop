package com.shop.mapper;

import com.shop.entity.Car_Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Mapper
@ResponseBody
public interface Car_Info_Mapper {
    List<Car_Info> getCar_InfoList(@Param("field_name")String field_name,
                                   @Param("field_value")String field_value,
                                   @Param("start")int start,
                                   @Param("page_size")int page_size);

    int deleteCar_Info(int goods_id);
    void insertACar(Car_Info car_info);
    int AddNum(int goods_id);
    int DeleteNum(int goods_id);
    void deleteAll();
    List<Car_Info> getAllList();

}

