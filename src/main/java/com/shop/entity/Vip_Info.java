package com.shop.entity;

public class Vip_Info {
    private int vip_style;
    private String vip_name;
    private double vip_discount;
    private int vip_value;

    public int getVip_id() {
        return vip_style;
    }

    public void setVip_id(int vip_id) {
        this.vip_style = vip_id;
    }

    public String getVip_name() {
        return vip_name;
    }

    public void setVip_name(String vip_name) {
        this.vip_name = vip_name;
    }

    public double getVip_discount() {
        return vip_discount;
    }

    public void setVip_discount(double vip_discount) {
        this.vip_discount = vip_discount;
    }

    public int getVip_value() {
        return vip_value;
    }

    public void setVip_value(int vip_value) {
        this.vip_value = vip_value;
    }
}
