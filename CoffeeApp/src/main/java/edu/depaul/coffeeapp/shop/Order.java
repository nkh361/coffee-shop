package edu.depaul.coffeeapp.shop;

import edu.depaul.coffeeapp.security.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private User customer;

    @ElementCollection
    private List<String> items;
}
