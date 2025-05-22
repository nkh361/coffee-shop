package edu.depaul.coffeeapp.security;

import jakarta.persistence.*;

/**
 * User entity
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String type;    // customer or shop

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return type;
    }


}
