package edu.depaul.coffeeapp.shop;

import edu.depaul.coffeeapp.shop.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API layer for orders
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    private OrderService orderService;

    /**
     * Responsible for invoking UC-003
     * @param order      Order object of customer order
     * @return           ResponseEntity of Order DTO
     * @throws Exception Throws exception if the order could not be fulfilled
     */
    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody Order order) throws Exception {
        OrderDTO orderDTO = orderService.placeOrder(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderStatus(@PathVariable Long id) throws Exception {
        throw new Exception("Not implemented yet");
    }
}
