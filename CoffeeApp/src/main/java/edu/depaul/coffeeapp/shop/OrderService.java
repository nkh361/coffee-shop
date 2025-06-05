package edu.depaul.coffeeapp.shop;

import edu.depaul.coffeeapp.notification.NotificationService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
     * @param order         Order object of order
     * @return              conversion of order to saved order
     * @throws Exception    order could not be placed
     */
    public OrderDTO placeOrder(Order order) throws Exception {
        try {
            order.setOrderTime(LocalDateTime.now());
            Order saved = orderRepository.save(order);

            return new OrderDTO(
                    saved.getId(),
                    saved.getCustomer(),
                    saved.getShopId(),
                    saved.getOrderTime(),
                    saved.getItems()
            );
        } catch (Exception e) {
            throw new Exception("Order could not be placed: " + e.getMessage());
        }
    }
}
