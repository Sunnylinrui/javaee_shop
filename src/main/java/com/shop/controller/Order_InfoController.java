package com.shop.controller;

import com.shop.entity.*;
import com.shop.service.Admin_InfoService;
import com.shop.service.Goods_InfoService;
import com.shop.service.Order_InfoService;
import com.shop.service.User_InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class Order_InfoController {
    @Autowired
    private Order_InfoService order_infoService;
    @Autowired
    private User_InfoService user_infoService;
    @Autowired
    private Goods_InfoService goods_infoService;
    @Autowired
    private Admin_InfoService admin_infoService;
    @RequestMapping("show_set_order")
    public String showSetOrder(HttpServletRequest request, Model model){
        int goods_id = Integer.parseInt(request.getParameter("goods_id"));
        int user_id =Integer.parseInt(request.getParameter("user_id"));
        List<Room_Num_Info> room_list = goods_infoService.getRoomNumList(goods_id);
        int room_count = goods_infoService.getRoomStyle();
        Integer[] room_num =new Integer[room_count];
        int count=room_list.size();
        for (int i=0;i<room_count;i++){
            room_num[i]=0;
        }
        for (int i=0;i<count;i++){
            room_num[room_list.get(i).getRoom_style()]=room_list.get(i).getRoom_num()-room_list.get(i).getRoom_book_num();
        }

        List<Book_room_info> book_room_infoList = new ArrayList<Book_room_info>(count);
        for (int i=0;i<count;i++){
            Book_room_info book_room_info = new Book_room_info();
            int room_book_num=room_num[room_list.get(i).getRoom_style()];
            book_room_info.setRoom_book_num(room_book_num);
            int room_style=room_list.get(i).getRoom_style();
            book_room_info.setRoom_style(room_style);
            String room_name1=goods_infoService.getRoomName(room_list.get(i).getRoom_style());
            int room_price=room_list.get(i).getRoom_price();
            book_room_info.setRoom_price(room_price);
            book_room_info.setRoom_name(room_name1);
            book_room_infoList.add(i,book_room_info);
        }
        Goods_Info goods_info=goods_infoService.getGoodsInfo(goods_id);
        User_Info user_info =user_infoService.getUserInfo(user_id);
        model.addAttribute("user_id",user_id);
        model.addAttribute("user_info",user_info);
        model.addAttribute("goods_id",goods_id);
        model.addAttribute("goods_info",goods_info);
        model.addAttribute("room_num",room_num);
        model.addAttribute("book_room_infoList",book_room_infoList);
        return "set_order";
    }

    //下单并跳转
    @RequestMapping("set_order")
    public String SetOrder(HttpServletRequest request, Model model, @DateTimeFormat(style="yyyy/MM/dd")Date date, RedirectAttributes attributes){
        int order_id = order_infoService.getMaxId()+1;//订单号
        int goods_id = Integer.parseInt(request.getParameter("goods_id"));//商品号
        int user_id =Integer.parseInt(request.getParameter("user_id"));//下单人
        int book_num=Integer.parseInt(request.getParameter("book_num"));//下单数量
        int book_room_num=Integer.parseInt(request.getParameter("book_room_num"));//商品下单
        int room_style=Integer.parseInt(request.getParameter("room_style"));//下单商品类型
        String book_phone=request.getParameter("book_phone");//收货电话
        String order_name=request.getParameter("order_name");//收货联系人
        String book_sfz=request.getParameter("book_sfz");//收货地址
        String goods_name=request.getParameter("goods_name");

        String book_year=request.getParameter("book_year");
        String book_month=request.getParameter("book_month");
        String book_day=request.getParameter("book_day");
        String book_time=book_year+"-"+book_month+"-"+book_day;


        String leave_year=request.getParameter("leave_year");
        String leave_month=request.getParameter("leave_month");
        String leave_day=request.getParameter("leave_day");
        String leave_time=leave_year+"-"+leave_month+"-"+leave_day;





        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");



        Date order_time=new Date();
        String order_time1=formatter.format(order_time);
        System.out.println(order_time1);
        int room_price=goods_infoService.getRoomPrice(goods_id,room_style);
        int vip_style=user_infoService.getAUser_vip(user_id);
        double discount = user_infoService.getDicount(vip_style);
        double price=book_room_num*room_price*discount;
        goods_infoService.updateRoomInfo(goods_id,book_room_num,room_style);
        goods_infoService.updateUserInfo(user_id,price);

        Order_Info order_info=new Order_Info();
        order_info.setOrder_id(order_id);
        order_info.setUser_id(user_id);
        order_info.setGoods_id(goods_id);
        order_info.setRoom_style(room_style);
        order_info.setRoom_num(book_room_num);
        order_info.setBook_num(book_num);
        order_info.setBook_phone(book_phone);
        order_info.setOrder_name(order_name);
        order_info.setOrder_time(order_time1);
        order_info.setBook_time( book_time);
        order_info.setLeave_time(leave_time);
        order_info.setBook_sfz(book_sfz);
        order_info.setOrder_state(0);
        order_info.setOrder_price(price);
        order_info.setGoods_name(goods_name);
        System.out.println(goods_name);
        order_infoService.insert(order_info);

        model.addAttribute("price",price);
        model.addAttribute("user_id",user_id);
//        model.addAttribute("user_id",user_id);
//        model.addAttribute("start_page", 1);
//        model.addAttribute("page_size", 6);
//        String user_name = user_infoService.getAUser_name(user_id);
//        int user_vip = user_infoService.getAUser_vip(user_id);
//        String vip = user_infoService.getvip(user_vip);
//        List<Goods_Info> goods_infoList = goods_infoService.getGoodsList(1,6,6);
//        model.addAttribute("goods_infoList",goods_infoList);
//        model.addAttribute("user_name",user_name);
//        model.addAttribute("vip",vip);
        return "jump";
    }
    @RequestMapping("show_user_order")
    public String ShowUserOrder(HttpServletRequest request, Model model){
        int user_id= Integer.parseInt(request.getParameter("user_id"));
        User_Info user_info=user_infoService.getUserInfo(user_id);
        model.addAttribute("user_info",user_info);
        List<Order_Info> order_infoList=order_infoService.getUserOrder(user_id);//获取该账号的所有订单
        int count=order_infoList.size();

//        List<Order_user_info> order_user_ok_infoList =new ArrayList<>();
//        List<Order_user_info> order_user_td_infoList =new ArrayList<>();
//        List<Order_user_info> order_user_ing_infoList =new ArrayList<>();
        List<Order_user_info> order_user_infoList =new ArrayList<>();
        for (int i=0;i<count;i++){
            Order_user_info order_user_info=new Order_user_info();

            int order_id=order_infoList.get(i).getOrder_id();
            order_user_info.setOrder_id(order_id);

            int goods_id=order_infoList.get(i).getGoods_id();
            Goods_Info goods_info = goods_infoService.getGoodsInfo(goods_id);
            String goods_name = goods_info.getGoods_name();
            order_user_info.setGoods_name(goods_name);

            String room_name = goods_infoService.getRoomName(order_infoList.get(i).getRoom_style());
            order_user_info.setRoom_name(room_name);

            order_user_info.setBook_time(order_infoList.get(i).getBook_time());
            order_user_info.setLeave_time(order_infoList.get(i).getLeave_time());

            int order_state = order_infoList.get(i).getOrder_state();
            if (order_state==0){
                order_user_info.setOrder_state("进行中");
            }
            if (order_state==1){
                order_user_info.setOrder_state("已完成");
            }
            if(order_state==2){
                order_user_info.setOrder_state("已退订");
            }
            order_user_infoList.add(order_user_info);
        }
        model.addAttribute("order_user_infoList",order_user_infoList);
        return "show_user_order";
    }
    @RequestMapping("delete_order")
    public String deleteOrder(HttpServletRequest request, Model model){
        int user_id= Integer.parseInt(request.getParameter("user_id"));
        int order_id= Integer.parseInt(request.getParameter("order_id"));
        order_infoService.deleteOrder(order_id);
        User_Info user_info=user_infoService.getUserInfo(user_id);
        int vip = user_infoService.getAUser_vip(user_id);
        String vip_style = user_infoService.getvip(vip);
        model.addAttribute("user_id",user_id);
        model.addAttribute("user_info",user_info);
        model.addAttribute("vip_style",vip_style);
        return "user_info";
    }
    @RequestMapping("td")
    public String TD(HttpServletRequest request, Model model, RedirectAttributes attributes){
        int user_id= Integer.parseInt(request.getParameter("user_id"));
        int order_id= Integer.parseInt(request.getParameter("order_id"));
        int goods_id= Integer.parseInt(request.getParameter("goods_id"));
        Order_Info order_info =order_infoService.getAUserOrder(order_id);
        double price = order_info.getOrder_price();
        System.out.println(price);
        price = price*0.5;
        admin_infoService.addGoodsMoney(goods_id,price);
        order_infoService.addMoney(user_id,price);
        order_infoService.setTD(order_id);
        User_Info user_info=user_infoService.getUserInfo(user_id);
        int vip = user_infoService.getAUser_vip(user_id);
        String vip_style = user_infoService.getvip(vip);
        attributes.addAttribute("user_id",user_id);
        model.addAttribute("user_id",user_id);
        model.addAttribute("user_info",user_info);
        model.addAttribute("vip_style",vip_style);
        attributes.addAttribute("vip_style",vip_style);
        request.getSession().setAttribute("user_info",user_info);
        return "redirect:show_user_info";
    }
    @RequestMapping("show_order_info")
    public String showOrderInfo(HttpServletRequest request, Model model){
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        int user_id =Integer.parseInt(request.getParameter("user_id"));
        Order_Info order_info=order_infoService.getAUserOrder(order_id);
        int room_style =order_info.getRoom_style();
        String room_name = goods_infoService.getRoomName(room_style);
        int order_state = order_info.getOrder_state();
        String order_state1="";
        if (order_state==0){
            order_state1="进行中";
        }
        if (order_state==1){
            order_state1="已完成";
        }
        if(order_state==2){
            order_state1="已退订";
        }
        model.addAttribute("order_info",order_info);
        model.addAttribute("user_id",user_id);
        model.addAttribute("order_state",order_state1);
        model.addAttribute("room_name",room_name);
        if (order_state==2||order_state==1){
            return "user_order_info";
        }else {
            return "user_order_info_ing";
        }
    }



}
