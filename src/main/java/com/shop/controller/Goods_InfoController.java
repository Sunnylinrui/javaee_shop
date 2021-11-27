package com.shop.controller;

import com.shop.entity.Car_Info;
import com.shop.entity.Goods_Info;
import com.shop.entity.Room_Num_Info;
import com.shop.entity.User_Info;
import com.shop.service.Goods_InfoService;
import com.shop.service.User_InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class Goods_InfoController {
    @Autowired
    private Goods_InfoService goods_infoService;
    @Autowired
    private User_InfoService user_infoService;
    @RequestMapping("show_goods_list")
    public String ShowGoods(HttpServletRequest request, Model model){
        int start_page;
        int page_size;
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String user_name = user_infoService.getAUser_name(user_id);
        int user_vip = user_infoService.getAUser_vip(user_id);
        String vip = user_infoService.getvip(user_vip);
        if (request.getParameter("start_page") == null && request.getParameter("page_size") == null) {
            start_page = 1;
            page_size = 6;
        } else {
            start_page = Integer.parseInt(request.getParameter("start_page"));
            page_size = Integer.parseInt(request.getParameter("page_size"));
        }
        model.addAttribute("start_page", start_page);
        model.addAttribute("page_size", page_size);
        model.addAttribute("user_id",user_id);
        model.addAttribute("user_name",user_name);
        model.addAttribute("vip",vip);
        List<Goods_Info> goods_infoList = goods_infoService.getGoodsList(start_page, page_size,6);
        model.addAttribute("goods_infoList",goods_infoList);
        return "main";
    }
    @RequestMapping("show_user_info")
    public String showUserInfo(HttpServletRequest request, Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        User_Info user_info=user_infoService.getUserInfo(user_id);
        int vip = user_infoService.getAUser_vip(user_id);
        String vip_style = user_infoService.getvip(vip);
        model.addAttribute("user_id",user_id);
        model.addAttribute("user_info",user_info);
        model.addAttribute("vip_style",vip_style);
        return "user_info";
    }
    @RequestMapping("show_goods_info")
    public String showGoodsInfo(HttpServletRequest request, Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int goods_id= Integer.parseInt(request.getParameter("goods_id"));
        Goods_Info goods_info=goods_infoService.getGoodsInfo(goods_id);

        List<Room_Num_Info> goods_infoList = goods_infoService.getRoomNumList(goods_id);
        int count = goods_infoList.size();
        System.out.println(goods_info.getGoods_address_url());
        int room_count=0;
        int room_book=0;
        for (int i = 0;i<count;i++){
            int temp = goods_infoList.get(i).getRoom_num()-goods_infoList.get(i).getRoom_book_num();
            room_book+=temp;
            room_count=room_count+goods_infoList.get(i).getRoom_num();
        }
        System.out.println("总"+room_count+"剩余"+room_book);
        model.addAttribute("room_count",room_count);
        model.addAttribute("room_book",room_book);
        model.addAttribute("goods_info",goods_info);
        model.addAttribute("user_id",user_id);
        return "goods_info";
    }


     //跳转至购物车页面
    @RequestMapping("add_order")
    public String AddOrder(HttpServletRequest request,RedirectAttributes attributes){

    Car_Info car_info = new Car_Info();
    car_info.setGoods_id(Integer.parseInt(request.getParameter("goods_id")));
    car_info.setGoods_img(request.getParameter("goods_img"));
    car_info.setGoods_name(request.getParameter("goods_name"));
    car_info.setGoods_price(request.getParameter("goods_price"));
    car_info.setGoods_num(1);
    System.out.println(car_info.getGoods_name()+car_info.getGoods_img()+car_info.getGoods_price()+car_info.getGoods_num());
    goods_infoService.insertACar(car_info);
    attributes.addAttribute("start_page",1);
    attributes.addAttribute("page_size",6);
    attributes.addAttribute("field_name","all");
    attributes.addAttribute("field_value","");

    return "redirect:show_car_list";
}

    //添加入购物车
    @RequestMapping("insert_car")
    public String InsertCar(HttpServletRequest request,RedirectAttributes attributes){
        Car_Info car_info = new Car_Info();
        car_info.setGoods_id(Integer.parseInt("goods_id"));
        car_info.setGoods_img(request.getParameter("goods_img"));
        car_info.setGoods_name(request.getParameter("goods_name"));
        car_info.setGoods_price(request.getParameter("goods_price"));
        car_info.setGoods_num(Integer.parseInt(request.getParameter("input-num")));
        System.out.println(car_info.getGoods_name());
        goods_infoService.insertACar(car_info);
        attributes.addAttribute("start_page",1);
        attributes.addAttribute("page_size",10);
        attributes.addAttribute("field_name","all");
        attributes.addAttribute("field_value","");

        return "redirect:show_car_list";
    }



}
