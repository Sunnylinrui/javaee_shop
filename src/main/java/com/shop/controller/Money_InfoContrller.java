package com.shop.controller;


import com.shop.service.Money_InfoService;
import com.shop.service.Car_InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Money_InfoContrller {
    @Autowired
    private Money_InfoService money_infoService;
    @Autowired
    private Car_InfoService car_infoService;
}
