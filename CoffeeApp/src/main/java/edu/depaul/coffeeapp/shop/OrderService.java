package edu.depaul.coffeeapp.shop;

import edu.depaul.coffeeapp.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic for shop system
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private NotificationService notificationService;

    /**
     * Responsible for triggering the order mechanism.
     * Method should convert to an order entity and set a new status for the order. After saving the order to
     * repository, the notification service should notify the shop.
     * @param orderDTO      DTO of order
     * @return              conversion of order to saved order
     * @throws Exception
     */
    public OrderDTO placeOrder(OrderDTO orderDTO) throws Exception {
        throw new Exception("Not implemented yet");
    }
}
