package com.shop.controller;

import com.shop.entity.*;
import com.shop.service.Admin_InfoService;
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
public class Admin_InfoController {
    @Autowired
    private Admin_InfoService admin_infoService;
    @Autowired
    private Goods_InfoService goods_infoService;
    @Autowired
    private User_InfoService user_infoService;
    @RequestMapping("login_admin")
    public String showLoginAdmin(){
        return "admin_login";
    }
    @RequestMapping("check_login_admin")
    public String checkLoginAdmin(HttpServletRequest request, Model model, RedirectAttributes attributes){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        String admin_pwd = request.getParameter("admin_pwd");
        Admin_Info admin_info = admin_infoService.checkLogin(admin_id,admin_pwd);
        if (admin_info==null){
            return "redirect:login_admin";
        }else {
            request.getSession().setAttribute("admin_info",admin_info);
            attributes.addAttribute("admin_id",admin_id);
            return "redirect:show_admin_main";
        }
    }

    //展示管理主页
    @RequestMapping("show_admin_main")
    public String showAdminMain(HttpServletRequest request, Model model){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        return "admin_main";
    }

    //展示营收额
    @RequestMapping("show_money")
    public String showMoney(HttpServletRequest request, Model model){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        List<Goods_Info> goods_infoList = admin_infoService.getAllGoodsInfo();
        model.addAttribute("goods_infoList",goods_infoList);
        return "admin_goods_money";
    }
    //展示订单列表
    @RequestMapping("show_order")
    public String showOrder(HttpServletRequest request, Model model){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        String field_name = request.getParameter("field_name");
        String field_value = request.getParameter("field_value");
        int start_page = Integer.parseInt(request.getParameter("start_page"));
        int page_size = Integer.parseInt(request.getParameter("page_size"));
        List<Order_Info> order_infoList =
                admin_infoService.getAllorderInfo(field_name, field_value, start_page, page_size);
        List<Order_Info> order_ing_infoList =
                admin_infoService.getAllorderIngInfo(field_name, field_value, start_page, page_size);
        List<Order_Info> order_td_infoList = admin_infoService.getAllorderTdInfo(field_name, field_value, start_page, page_size);
        model.addAttribute("order_info_list", order_infoList);
        model.addAttribute("order_info_ing_list", order_ing_infoList);
        model.addAttribute("order_info_td_list", order_td_infoList);
        model.addAttribute("field_name", field_name);
        model.addAttribute("field_value", field_value);
        model.addAttribute("start_page", start_page);
        model.addAttribute("page_size", page_size);
        return "admin_show_order";
    }
    //删除会员
    @RequestMapping("delete_user")
    public String DeleteUser(HttpServletRequest request, Model model, RedirectAttributes attributes){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        int user_id=Integer.parseInt(request.getParameter("user_id"));
        admin_infoService.delete_user(user_id);
        attributes.addAttribute("admin_id",admin_id);
        return "redirect:show_user";
    }
    //展示会员信息
    @RequestMapping("show_user")
    public String showUser(HttpServletRequest request, Model model){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        List<User_List> userLists = admin_infoService.getAllUserList();
        model.addAttribute("user_list",userLists);
        return "admin_show_user";

    }
    //更改会员信息
    @RequestMapping("show_user_edit")
    public String showUserEdit(HttpServletRequest request, Model model){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        List<Vip_Info> vip_list = admin_infoService.getVipList();
        model.addAttribute("vip_list", vip_list);
        User_Info user_info = user_infoService.getUserInfo(user_id);
        model.addAttribute("user_info",user_info);
        return "admin_edit_user";
    }
    //保存更改会员信息
    @RequestMapping("save_edit_user_info")
    public String SaveEditUser(HttpServletRequest request, RedirectAttributes attributes){

        int user_id = Integer.parseInt(request.getParameter("user_id"));
        System.out.println(user_id);
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        String user_pwd = request.getParameter("user_pwd");
        String user_name =request.getParameter("user_name");
        String user_phone =request.getParameter("user_phone");
        String user_sfz  =request.getParameter("user_sfz");
        double user_money=Double.parseDouble(request.getParameter("user_money"));
        int vip_style = Integer.parseInt(request.getParameter("vip_style"));
        User_Info user_info = new User_Info();
        user_info.setUser_id(user_id);
        user_info.setUser_name(user_name);
        user_info.setUser_pwd(user_pwd);
        user_info.setUser_phone(user_phone);
        user_info.setUser_sfz(user_sfz);
        user_info.setVip_style(vip_style);
        user_info.setUser_money(user_money);
        admin_infoService.updateUser(user_info);
        attributes.addAttribute("admin_id",admin_id);
        return "redirect:show_user";
    }
    //删除订单
    @RequestMapping("delete_order_admin")
    public String deleteOrder(HttpServletRequest request, Model model, RedirectAttributes attributes){
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        admin_infoService.deleteOrder(order_id);

        int  start_page = Integer.parseInt(request.getParameter("start_page"));
        int page_size = Integer.parseInt(request.getParameter("page_size"));
        String field_name = request.getParameter("field_name");
        String field_value = request.getParameter("field_value");
        attributes.addAttribute("admin_id",admin_id);
        attributes.addAttribute("start_page", start_page);
        attributes.addAttribute("page_size", page_size);
        attributes.addAttribute("field_name", field_name);
        attributes.addAttribute("field_value", field_value);
        return "redirect:show_order";
    }
    //完成订单
    @RequestMapping("order_ok")
    public String OrderOK(HttpServletRequest request, Model model, RedirectAttributes attributes){
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        int goods_id = Integer.parseInt(request.getParameter("goods_id"));
        double order_price=Double.parseDouble(request.getParameter("order_price"));
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));

        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);

        admin_infoService.setOrderState(order_id);
        admin_infoService.addGoodsMoney(goods_id,order_price);

        int  start_page = Integer.parseInt(request.getParameter("start_page"));
        int page_size = Integer.parseInt(request.getParameter("page_size"));
        String field_name = request.getParameter("field_name");
        String field_value = request.getParameter("field_value");
        attributes.addAttribute("admin_id",admin_id);
        attributes.addAttribute("start_page", start_page);
        attributes.addAttribute("page_size", page_size);
        attributes.addAttribute("field_name", field_name);
        attributes.addAttribute("field_value", field_value);
        return "redirect:show_order";
    }
    //展示添加商品页面
    @RequestMapping("show_add")
    public String showAddGoods(HttpServletRequest request, Model model){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        return "admin_add_goods";
    }
    //保存添加商品操作
    @RequestMapping("save_add_goods")
    public String saveAddGoods(HttpServletRequest request, Model model, RedirectAttributes attributes){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        int goods_id=goods_infoService.getMaxGoodsid()+1;
        String goods_name=request.getParameter("goods_name");
        int goods_score = Integer.parseInt(request.getParameter("goods_score"));
        String goods_address=request.getParameter("goods_address");
        String goods_img=request.getParameter("goods_img");
        String goods_introduction =request.getParameter("goods_introduction");
        Goods_Info goods_info= new Goods_Info();
        goods_info.setGoods_id(goods_id);
        goods_info.setGoods_name(goods_name);
        goods_info.setGoods_score(goods_score);
        goods_info.setGoods_img(goods_img);
        goods_info.setGoods_address(goods_address);
        goods_info.setGoods_money(0);
        goods_info.setGoods_introduction(goods_introduction);
        goods_infoService.insertGoods(goods_info);
        model.addAttribute("admin_id",admin_id);
        attributes.addAttribute("admin_id",admin_id);
        return "redirect:show_admin_main";
    }
    //展示设置商品类型
    @RequestMapping("show_set_room")
    public String showSetRoom(HttpServletRequest request, Model model){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        List<Goods_Info> goods_infoList = admin_infoService.getAllGoodsInfo();
        model.addAttribute("goods_infoList",goods_infoList);
        return "admin_set_goods_list";
    }
    //更改商品类型
    @RequestMapping("show_set_room_admin")
    public String ShowSetRoomAdmin(HttpServletRequest request, Model model){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        int goods_id = Integer.parseInt(request.getParameter("goods_id"));
        Goods_Info goods_info = goods_infoService.getGoodsInfo(goods_id);
        model.addAttribute("goods_info",goods_info);
        List<Room_style_Info> room_style_infoList =admin_infoService.getRoomStyle();
        List<Book_room_info> room_infoList = admin_infoService.getRoomInfo(goods_id);
        model.addAttribute("room_infoList",room_infoList);
        model.addAttribute("room_style_infoList",room_style_infoList);
        return "admin_set_room";
    }
    //保存商品类型数量修改
    @RequestMapping("save_room_num")
    public String SaveRoomNum(HttpServletRequest request, Model model, RedirectAttributes attributes){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        int goods_id =Integer.parseInt(request.getParameter("goods_id"));
        int room_style = Integer.parseInt(request.getParameter("room_style"));
        int room_num =Integer.parseInt(request.getParameter("room_num"));
        int room_price =Integer.parseInt(request.getParameter("room_price"));
        List<Book_room_info> room_infoList = admin_infoService.getRoomInfo(goods_id);
        int count = room_infoList.size();
        int isFull=0;
        for (int i = 0;i<count;i++){
            if (room_style==room_infoList.get(i).getRoom_style()) isFull=1;
        }
        if (isFull==1){
            admin_infoService.updateRoom(goods_id,room_style,room_num,room_price);
        }else {
            Room_Num_Info room_num_info  =new Room_Num_Info();
            room_num_info.setGoods_id(goods_id);
            room_num_info.setRoom_num(room_num);
            room_num_info.setRoom_price(room_price);
            room_num_info.setRoom_book_num(0);
            room_num_info.setRoom_style(room_style);
            admin_infoService.insertRoom(room_num_info);
        }

        model.addAttribute("goods_id",goods_id);
        attributes.addAttribute("admin_id",admin_id);
        attributes.addAttribute("goods_id",goods_id);
        return "redirect:show_set_room_admin";
    }

    //展示添加公告页面
    @RequestMapping("show_add_title")
    public String showAddTitle(HttpServletRequest request, Model model){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        return "admin_add_title";
    }
    //展示设置公告页面
    @RequestMapping("show_set_title")
    public String showSetTitle(HttpServletRequest request, Model model){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        List<Title_Info> title_infoList = admin_infoService.getAllTitleInfo();
        model.addAttribute("title_infoList",title_infoList);
        return "admin_set_title_list";
    }

    //进入更改公告信息
    @RequestMapping("show_set_title_admin")
    public String ShowSetTitleAdmin(HttpServletRequest request, Model model){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        int title_id = Integer.parseInt(request.getParameter("title_id"));
        Title_Info title_info = admin_infoService.getTitleInfo(title_id);
        model.addAttribute("title_info",title_info);
        return "admin_set_title";
    }
    //保存添加公告信息
    @RequestMapping("save_add_title")
    public String saveAddTitle(HttpServletRequest request, Model model, RedirectAttributes attributes){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        int title_id=admin_infoService.getMaxTitleid()+1;
        String title_name=request.getParameter("title_name");
        String title_head=request.getParameter("title_head");
        String title_message=request.getParameter("title_message");
        String title_date=request.getParameter("title_date");
        Title_Info title_info= new Title_Info();
        title_info.setTitle_id(title_id);
        title_info.setTitle_name(title_name);
        title_info.setTitle_head(title_head);
        title_info.setTitle_message(title_message);
        title_info.setTitle_date(title_date);
        admin_infoService.insertTitle(title_info);
        model.addAttribute("admin_id",admin_id);
        attributes.addAttribute("admin_id",admin_id);
        return "redirect:show_admin_main";
    }
    //保存修改后公告信息
    @RequestMapping("save_title")
    public String SaveTitle(HttpServletRequest request, Model model, RedirectAttributes attributes){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));

       /* Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);*/
        int title_id =Integer.parseInt(request.getParameter("title_id"));
        String title_name =request.getParameter("title_name");
        String title_head =request.getParameter("title_head");
        String title_message =request.getParameter("title_message");
        String title_date =request.getParameter("title_date");
        Title_Info title_info = new Title_Info();
        title_info.setTitle_id(title_id);
        title_info.setTitle_name(title_name);
        title_info.setTitle_head(title_head);
        title_info.setTitle_message(title_message);
        title_info.setTitle_date(title_date);
        admin_infoService.updateTitle(title_info);
        model.addAttribute("title_id",title_id);
        attributes.addAttribute("admin_id",admin_id);
        attributes.addAttribute("title_id",title_id);
        return "redirect:show_set_title_admin";
    }
    //删除公告
    @RequestMapping("delete_title")
    public String DeleteTitle(HttpServletRequest request, Model model, RedirectAttributes attributes){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
        model.addAttribute("admin_id",admin_id);
        model.addAttribute("admin_info",admin_info);
        int title_id=Integer.parseInt(request.getParameter("title_id"));
        admin_infoService.delete_title(title_id);
        attributes.addAttribute("admin_id",admin_id);
        return "redirect:show_set_title";
    }

 /*   @RequestMapping("save_add_goods")
    public String saveAddGoods(HttpServletRequest request, Model model, RedirectAttributes attributes){
        int admin_id = Integer.parseInt(request.getParameter("admin_id"));
        int goods_id=goods_infoService.getMaxGoodsid()+1;
        String goods_name=request.getParameter("goods_name");
        int goods_score = Integer.parseInt(request.getParameter("goods_score"));
        String goods_address=request.getParameter("goods_address");
        String goods_img=request.getParameter("goods_img");
        String goods_introduction =request.getParameter("goods_introduction");
        Goods_Info goods_info= new Goods_Info();
        goods_info.setGoods_id(goods_id);
        goods_info.setGoods_name(goods_name);
        goods_info.setGoods_score(goods_score);
        goods_info.setGoods_img(goods_img);
        goods_info.setGoods_address(goods_address);
        goods_info.setGoods_money(0);
        goods_info.setGoods_introduction(goods_introduction);
        goods_infoService.insertGoods(goods_info);
        model.addAttribute("admin_id",admin_id);
        attributes.addAttribute("admin_id",admin_id);
        return "redirect:show_admin_main";
    }*/
    /*int user_id = Integer.parseInt(request.getParameter("user_id"));
        System.out.println(user_id);
    int admin_id = Integer.parseInt(request.getParameter("admin_id"));
    String user_pwd = request.getParameter("user_pwd");
    String user_name =request.getParameter("user_name");
    String user_phone =request.getParameter("user_phone");
    String user_sfz  =request.getParameter("user_sfz");
    double user_money=Double.parseDouble(request.getParameter("user_money"));
    int vip_style = Integer.parseInt(request.getParameter("vip_style"));
    User_Info user_info = new User_Info();
        user_info.setUser_id(user_id);
        user_info.setUser_name(user_name);
        user_info.setUser_pwd(user_pwd);
        user_info.setUser_phone(user_phone);
        user_info.setUser_sfz(user_sfz);
        user_info.setVip_style(vip_style);
        user_info.setUser_money(user_money);
        admin_infoService.updateUser(user_info);
        attributes.addAttribute("admin_id",admin_id);
        return "redirect:show_user";*/
    //展示前端公告页面
 @RequestMapping("show_title")
 public String showTitle(HttpServletRequest request, Model model){
     /*int admin_id = Integer.parseInt(request.getParameter("admin_id"));
     Admin_Info admin_info=admin_infoService.getAdminInfo(admin_id);
     model.addAttribute("admin_id",admin_id);
     model.addAttribute("admin_info",admin_info);*/
     List<Title_Info> title_infoList = admin_infoService.getAllTitleInfo();
     model.addAttribute("title_infoList",title_infoList);
     return "title";
 }
}
