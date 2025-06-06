package edu.depaul.coffeeapp.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private String customerName;
    private double shopId;
    private LocalDateTime orderTime;
    private List<String> items;
    private OrderStatus orderStatus;
}
