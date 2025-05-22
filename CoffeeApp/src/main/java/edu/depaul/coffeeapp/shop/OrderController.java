package edu.depaul.coffeeapp.shop;

import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Responsible for invoking UC-003
     * @param orderDTO      DTO of order
     * @return
     * @throws Exception
     */
    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        throw new Exception("Not implemented yet");
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderStatus(@PathVariable Long id) throws Exception {
        throw new Exception("Not implemented yet");
    }
}
