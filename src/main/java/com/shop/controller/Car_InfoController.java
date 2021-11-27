package com.shop.controller;

import com.shop.entity.Car_Info;
import com.shop.entity.Goods_Info;
import com.shop.entity.Money_info;
import com.shop.service.Car_InfoService;
import com.shop.service.Goods_InfoService;
import com.shop.service.Money_InfoService;
import com.shop.service.User_InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class Car_InfoController {
    @Autowired
    private Car_InfoService car_infoService;
    @Autowired
    private Money_InfoService money_infoService;
    @Autowired
    private User_InfoService user_infoService;
    @Autowired
    private Goods_InfoService goods_infoService;
    @RequestMapping("show_car_list")
    public String showCarList(HttpServletRequest request, Model model){
        String field_name = request.getParameter("field_name");
        String field_value = request.getParameter("field_value");
        /*int user_id=Integer.parseInt(request.getParameter("user_id"));*/
        int start_page = Integer.parseInt(request.getParameter("start_page"));
        int page_size = Integer.parseInt(request.getParameter("page_size"));
        List<Car_Info> car_infoList = car_infoService.
                getCarListByCondition(field_name,field_value,start_page,page_size);
        model.addAttribute("car_info_list",car_infoList);
        model.addAttribute("field_name",field_name);
        model.addAttribute("field_value",field_value);
        model.addAttribute("start_page", start_page);
        model.addAttribute("page_size", page_size);
        /*model.addAttribute("user_id",user_id);*/

        return "show_car_list";
    }
    @RequestMapping("delete_car_info")
    public String deleteCar_Info(HttpServletRequest request, RedirectAttributes attributes){
        int flag = 0;
        int goods_id = Integer.parseInt(request.getParameter("goods_id"));
        flag = car_infoService.deleteGoods_Info(goods_id);
        System.out.println(goods_id);
        if(flag>0){
            attributes.addAttribute("field_name",request.getParameter("field_name"));
            attributes.addAttribute("field_value",request.getParameter("field_value"));
            attributes.addAttribute("start_page",request.getParameter("start_page"));
            attributes.addAttribute("page_size",request.getParameter("page_size"));
            return "redirect:show_car_list";

        }else return "error";
    }
    @RequestMapping("add_num")
    public String AddNum(HttpServletRequest request,RedirectAttributes attributes){
        int id = Integer.parseInt(request.getParameter("goods_id"));
        int flag = 0;
        flag = car_infoService.AddNum(id);
        if(flag>0){
            attributes.addAttribute("field_name",request.getParameter("field_name"));
            attributes.addAttribute("field_value",request.getParameter("field_value"));
            attributes.addAttribute("start_page",request.getParameter("start_page"));
            attributes.addAttribute("page_size",request.getParameter("page_size"));
            return "redirect:show_car_list";

        }else return "error";
    }
    @RequestMapping("delete_num")
    public String DeleteNum(HttpServletRequest request,RedirectAttributes attributes){
        int id = Integer.parseInt(request.getParameter("goods_id"));
        int num = Integer.parseInt(request.getParameter("goods_num"));
        int flag = 0;
        System.out.println(num);
        if (num>1){
            flag = car_infoService.DeleteNum(id);
        }else {
            attributes.addAttribute("field_name",request.getParameter("field_name"));
            attributes.addAttribute("field_value",request.getParameter("field_value"));
            attributes.addAttribute("start_page",request.getParameter("start_page"));
            attributes.addAttribute("page_size",request.getParameter("page_size"));
            car_infoService.deleteGoods_Info(Integer.parseInt(String.valueOf(id)));
        }
        if(flag>0){
            attributes.addAttribute("field_name",request.getParameter("field_name"));
            attributes.addAttribute("field_value",request.getParameter("field_value"));
            attributes.addAttribute("start_page",request.getParameter("start_page"));
            attributes.addAttribute("page_size",request.getParameter("page_size"));
        }
        return "redirect:show_car_list";
    }

    @RequestMapping("deleteAll")
    public  String DeleteAll(HttpServletRequest request,RedirectAttributes attributes){
        car_infoService.deleteAll();
        attributes.addAttribute("field_name",request.getParameter("field_name"));
        attributes.addAttribute("field_value",request.getParameter("field_value"));
        attributes.addAttribute("start_page",request.getParameter("start_page"));
        attributes.addAttribute("page_size",request.getParameter("page_size"));
        return "redirect:show_car_list";
    }
    @RequestMapping("/sum")
    public String sum(Model model){
        List<Car_Info> car_infoList = car_infoService.getAllList();
        List<Integer> car_info_num = car_infoList.stream().map(Car_Info::getGoods_num).collect(Collectors.toList()
        );
        List<String> car_info_price = car_infoList.stream().map(Car_Info::getGoods_price).collect(Collectors.toList());
        double sum = 0;
        int count = car_infoList.size();
        for (int i = 0;i<count;i++){
            double temp = Double.parseDouble(car_info_price.get(i))*car_info_num.get(i);
            sum+=temp;
        }
        System.out.println(sum);
        money_infoService.insertMoney(sum);
        car_infoService.deleteAll();
        Money_info money_infoList = new Money_info();
        money_infoList.setMoney(sum);
        model.addAttribute("money_info_list",money_infoList);
        model.addAttribute("money",sum);
        return "car_zhongzhuan";
    }
    @RequestMapping("/returnCar")
    public String ReturnCar(HttpServletRequest request,RedirectAttributes attributes){
        money_infoService.deleteMoney();
        attributes.addAttribute("field_name",request.getParameter("field_name"));
        attributes.addAttribute("field_value",request.getParameter("field_value"));
        attributes.addAttribute("start_page",request.getParameter("start_page"));
        attributes.addAttribute("page_size",request.getParameter("page_size"));
        return "redirect:show_car_list";
    }

  /*  @RequestMapping("show_main")
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
    }*/

    @RequestMapping("show_return")
    public String showRuturn(HttpServletRequest request, Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        model.addAttribute("user_id",user_id);
        return "show_car_list";
    }

}

