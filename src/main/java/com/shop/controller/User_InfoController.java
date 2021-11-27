package com.shop.controller;

import com.shop.entity.Goods_Info;
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
public class User_InfoController {
    @Autowired
    private User_InfoService user_infoService;
    @Autowired
    private Goods_InfoService goods_infoService;
    @RequestMapping("show_login")
    public String showLogin(){
        return "login";
    }
    @RequestMapping("show_login_admin")
    public String showAdmin(){
        return "login_admin";
    }
    @RequestMapping("check_login")
    public String checkLogin(HttpServletRequest request, RedirectAttributes attributes){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String user_pwd = request.getParameter("user_pwd");
        User_Info user_info = user_infoService.checkLogin(user_id,user_pwd);
        if (user_info==null){
            return "redirect:show_login";
        }else {
            request.getSession().setAttribute("user_info",user_info);
            attributes.addAttribute("user_id",user_id);
            return "redirect:show_main";
        }
    }
    @RequestMapping("show_main")
    public String showMain(HttpServletRequest request, Model model){
        int user_id=Integer.parseInt(request.getParameter("user_id"));
        String user_name = user_infoService.getAUser_name(user_id);
        int user_vip = user_infoService.getAUser_vip(user_id);
        String vip = user_infoService.getvip(user_vip);
        model.addAttribute("start_page", 1);
        model.addAttribute("page_size", 6);
        model.addAttribute("user_id",user_id);
        List<Goods_Info> goods_infoList = goods_infoService.getGoodsList(1,6,6);
        model.addAttribute("goods_infoList",goods_infoList);
        model.addAttribute("user_name",user_name);
        model.addAttribute("vip",vip);
        return "main";
    }
    @RequestMapping("show_register")
    public String showRegister(){
        return "register";
    }
    @RequestMapping("check_register")
    public String CheckRegister(HttpServletRequest request){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String user_pwd = request.getParameter("user_pwd");
        String user_name =request.getParameter("user_name");
        String user_phone =request.getParameter("user_phone");
        String user_sfz  =request.getParameter("user_sfz");
        User_Info user_info = new User_Info();
        user_info.setUser_id(user_id);
        user_info.setUser_name(user_name);
        user_info.setUser_pwd(user_pwd);
        user_info.setUser_phone(user_phone);
        user_info.setUser_sfz(user_sfz);
        user_info.setVip_style(0);
        user_info.setUser_money(0);
        user_infoService.register(user_info);
        return "redirect:show_login";
    }
    @RequestMapping("show_deposit")
    public String showDeposit(HttpServletRequest request, Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        model.addAttribute("user_id",user_id);
        return "deposit";
    }
    @RequestMapping("deposit")
    public String Deposit(HttpServletRequest request, Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int money = Integer.parseInt(request.getParameter("money"));
        user_infoService.deposit(money,user_id);
        User_Info user_info=user_infoService.getUserInfo(user_id);
        model.addAttribute("user_id",user_id);
        model.addAttribute("user_info",user_info);
        return "user_info";
    }
    @RequestMapping("show_buy_vip")
    public String showBuyVip(HttpServletRequest request, Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        model.addAttribute("user_id",user_id);
        return "vip_buy";
    }
    @RequestMapping("buy_vip")
    public String BuyVip(HttpServletRequest request, Model model, RedirectAttributes attributes){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int vip_style = Integer.parseInt(request.getParameter("vip_style"));
        int money = user_infoService.getVipValue(vip_style);
        System.out.println(money);
        User_Info user_info=user_infoService.getUserInfo(user_id);
        model.addAttribute("user_id",user_id);
        model.addAttribute("user_info",user_info);
        if (vip_style>user_info.getVip_style()){
            user_infoService.buy_vip(money,user_id,vip_style);
            return "user_info";
        }else {
            request.getSession().setAttribute("user_info",user_info);
            attributes.addAttribute("user_id",user_id);
            return "redirect:show_deposit";
        }
    }
    @RequestMapping("show_edit_user")
    public String showEditUser(HttpServletRequest request, Model model){
        int user_id=Integer.parseInt(request.getParameter("user_id"));
        User_Info user_info =user_infoService.getUserInfo(user_id);
        model.addAttribute("user_id",user_id);
        model.addAttribute("user_info",user_info);
        return "edit_user";
    }

    /*  @RequestMapping("show_user_order")
      public String showUsersOrder(HttpServletRequest request, Model model){
          int user_id=Integer.parseInt(request.getParameter("user_id"));
          User_Info user_info =user_infoService.getUserInfo(user_id);
          model.addAttribute("user_id",user_id);
          model.addAttribute("user_info",user_info);
          return "show_user_order";
      }*/
/*    @RequestMapping("show_edit_pwd")
    public String showEditPwd(HttpServletRequest request, Model model){
        int user_id=Integer.parseInt(request.getParameter("user_id"));
        User_Info user_info =user_infoService.getUserInfo(user_id);
        model.addAttribute("user_id",user_id);
        return "edit_pwd";
    }*/
    @RequestMapping("edit_user_info")
    public String editUserInfo(HttpServletRequest request, Model model){
        int user_id=Integer.parseInt(request.getParameter("user_id"));
        model.addAttribute("user_id",user_id);
        User_Info user_info=user_infoService.getUserInfo(user_id);
        model.addAttribute("user_info",user_info);
        String user_name = request.getParameter("user_name");
        String user_phone = request.getParameter("user_phone");
        String user_sfz = request.getParameter("user_sfz");
        user_infoService.updateuserinfo(user_id,user_name,user_phone,user_sfz);
        return "user_info";
    }

    /*//评论
    @RequestMapping("save_add_discuss")
    public String saveAddDiscuss(HttpServletRequest request, Model model){
        *//*int user_id = Integer.parseInt(request.getParameter("user_id"));*//*
        String user_name=request.getParameter("user_name");
        String user_phone=request.getParameter("user_phone");
        String user_discuss=request.getParameter("user_discuss");
        User_Info user_info= new User_Info();
        *//*user_info.setUser_id(user_id);*//*
        user_info.setUser_name(user_name);
        user_info.setUser_phone(user_phone);
        user_info.setUser_discuss(user_discuss);
        user_infoService.insertDiscuss(user_info);
        model.addAttribute("user_info",user_info);
       *//* model.addAttribute("user_id",user_id);
        attributes.addAttribute("user_id",user_id);*//*
        return "goods_info";
    }*/
}
