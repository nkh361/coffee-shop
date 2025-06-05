package edu.depaul.coffeeapp.shop;

public class CoffeeItem {
    private String name;
    private double price;
    private String description;
    private double shopId;
    private String shopName;

    public CoffeeItem(String name, double price, String description, double shopId, String shopName) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.shopId = shopId;
        this.shopName = shopName;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public double getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
    }

}
