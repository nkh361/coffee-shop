package edu.depaul.coffeeapp.shop;

public enum OrderStatus {
    // NOTE: completed means the order has been picked up. after completion, order should be cleared from shop inbox
    NEW, PREPARING, READY, COMPLETED, CANCELLED
}
