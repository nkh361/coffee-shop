package edu.depaul.coffeeapp.shop;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private Long orderId;
    private String customerName;
    private double shopId;
    private LocalDateTime orderTime;
    private List<String> items;

    public OrderDTO(Long id, String customer, double shopId, LocalDateTime orderTime, List<String> items) {
        this.orderId = id;
        this.customerName = customer;
        this.shopId = shopId;
        this.orderTime = orderTime;
        this.items = items;
    }
}
