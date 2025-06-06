package edu.depaul.coffeeapp.shop;

import lombok.Data;
import java.util.List;

@Data
public class ReceiptDTO {
    private String customerName;
    private List<CoffeeItem> items;
    private double total;

    public ReceiptDTO(String customerName, List<CoffeeItem> items) {
        this.customerName = customerName;
        this.items = items;
        this.total = items.stream().mapToDouble(CoffeeItem::getPrice).sum();
    }
}
