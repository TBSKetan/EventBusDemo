package com.example.eventbusdemo;

public class CartEvent {
    public String cartItem;

    public CartEvent(String cartItem) {
        this.cartItem = cartItem;
    }
}
