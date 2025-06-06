package edu.depaul.coffeeapp.shop;

import edu.depaul.coffeeapp.security.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Setter
    @Getter
    private String customer;

    @Getter
    @Setter
    @ElementCollection
    private List<String> items;

    @Getter
    @Setter
    private double shopId;

    @Getter
    @Setter
    private LocalDateTime orderTime;

    @Getter
    @Setter
    private double total;

    public Order(String customer, List<String> items, double shopId) {
        this.customer = customer;
        this.items = items != null ? items : new ArrayList<>();
        this.shopId = shopId;
        this.orderTime = LocalDateTime.now();
        this.status = OrderStatus.NEW;
    }

    public Order() {

    }
}
