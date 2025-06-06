package edu.depaul.coffeeapp.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/receipt")
public class ReceiptController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public String showReceipt(@PathVariable Long id, Model model) throws Exception {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new Exception("Order not found"));

        OrderDTO receiptData = orderService.toDTO(order);
        model.addAttribute("order", receiptData);
        return "receipt";
    }
}
