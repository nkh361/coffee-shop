package edu.depaul.coffeeapp.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * Entity for notifications.
 * Each notification should be serialized (have an ID), timestamp, the order foreign key,
 * sender/receiver ID.
 */
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private Long senderId;
    private Long recipientId;
    private LocalDateTime sentAt = LocalDateTime.now();
}
