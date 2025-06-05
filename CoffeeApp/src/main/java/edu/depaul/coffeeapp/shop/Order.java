package edu.depaul.coffeeapp.shop;

import edu.depaul.coffeeapp.security.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
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
    @ManyToOne
    private User customer;

    @Getter
    @ElementCollection
    private List<String> items;

    @Getter
    private double shopId;

    @Getter
    @Setter
    private LocalDateTime orderTime;

    public String getCustomer() {
        return customer.getUsername();
    }

}
