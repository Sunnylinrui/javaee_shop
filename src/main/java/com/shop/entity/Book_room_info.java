package com.shop.entity;

public class Book_room_info {
    private int room_style;
    private String room_name;
    private int room_book_num;
    private String book_room_img;
    private int room_price;
    private int room_num;

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }

    public int getRoom_price() {
        return room_price;
    }

    public void setRoom_price(int room_price) {
        this.room_price = room_price;
    }

    public int getRoom_style() {
        return room_style;
    }

    public void setRoom_style(int room_style) {
        this.room_style = room_style;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getRoom_book_num() {
        return room_book_num;
    }

    public void setRoom_book_num(int room_book_num) {
        this.room_book_num = room_book_num;
    }

    public String getBook_room_img() {
        return book_room_img;
    }

    public void setBook_room_img(String book_room_img) {
        this.book_room_img = book_room_img;
    }
}
