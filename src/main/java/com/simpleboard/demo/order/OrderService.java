package com.simpleboard.demo.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}