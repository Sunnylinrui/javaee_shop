package com.shop.entity;

public class Room_Num_Info {
    private int goods_id;
    private int room_style;
    private int room_num;
    private int room_book_num;
    private int room_price;

    public int getRoom_price() {
        return room_price;
    }

    public void setRoom_price(int room_price) {
        this.room_price = room_price;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public Room_Num_Info() {
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getRoom_style() {
        return room_style;
    }

    public void setRoom_style(int room_style) {
        this.room_style = room_style;
    }

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }

    public int getRoom_book_num() {
        return room_book_num;
    }

    public void setRoom_book_num(int room_book_num) {
        this.room_book_num = room_book_num;
    }

}