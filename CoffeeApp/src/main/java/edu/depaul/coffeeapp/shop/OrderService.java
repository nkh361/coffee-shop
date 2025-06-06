package edu.depaul.coffeeapp.shop;

import edu.depaul.coffeeapp.notification.NotificationService;
import edu.depaul.coffeeapp.security.User;
import edu.depaul.coffeeapp.security.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Business logic for shop system
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CoffeeController coffeeController;

    /**
     * Responsible for triggering the order mechanism.
     * Method should convert to an order entity and set a new status for the order. After saving the order to
     * repository, the notification service should notify the shop.
     * @param order         Order object of order
     * @return              conversion of order to saved order
     * @throws Exception    order could not be placed
     */
    public OrderDTO placeOrder(Order order) throws Exception {
        try {
            if (order.getCustomer() == null || order.getCustomer().isEmpty()) {
                throw new Exception("Customer name is required");
            }

            Map<String, CoffeeItem> shopMenu = coffeeController.getMenuForShop("cafe" + (int) order.getShopId());
            if (shopMenu.isEmpty()) throw new Exception("Shop not found");

            // item validation
            List<String> validItems = new ArrayList<>();
            for (String item : order.getItems()) {
                if (shopMenu.containsKey(item)) {
                    validItems.add(item);
                } else {
                    throw new Exception("Item not found");
                }
            }

            if (validItems.isEmpty()) throw new Exception("Items not found");

            // update the fields
            order.setOrderTime(LocalDateTime.now());
            order.setItems(validItems);
            order.setStatus(OrderStatus.NEW);

            /**
             * TODO: add drop down menu for shop selection, then the order service is only serving for the current shop
             */

            /**
            for (Map.Entry<String, Map<String, CoffeeItem>> entry : coffeeMenuService.getAllMenus().entrySet()) {
                if (entry.getValue().containsKey(itemName)) {
                    System.out.println("Item " + itemName + " not found in current shop, but available at: " + entry.getKey());
                }
            }
             */

            double total = 0;
            for (String item : validItems) {
                total += shopMenu.get(item).getPrice();
            }
            order.setTotal(total);


            order.setOrderTime(LocalDateTime.now());
            Order saved = orderRepository.save(order);

            return toDTO(saved);
        } catch (Exception e) {
            throw new Exception("Order could not be placed: " + e.getMessage());
        }
    }

    /**
     * Helper function for making the DTO
     * @param order     Order object details
     * @return          OrderDTO type
     */
    public OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getCustomer(),
                order.getShopId(),
                order.getOrderTime(),
                order.getItems(),
                order.getStatus(),
                order.getTotal()
        );
    }
}
