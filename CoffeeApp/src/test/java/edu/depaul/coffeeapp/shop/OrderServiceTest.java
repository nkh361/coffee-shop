package edu.depaul.coffeeapp.shop;

import edu.depaul.coffeeapp.security.User;
import edu.depaul.coffeeapp.security.UserRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CoffeeController coffeeController;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testOrderPlacement() {
        // make the menus
        Map<String, Map<String, CoffeeItem>> coffeeMenu = new HashMap<>();
        Map<String, CoffeeItem> cafe1 = new HashMap<>();

        CoffeeItem cafe1Latte = new CoffeeItem(
                "Latte", 2.99, "espresso and milk", 123123, "cafe1"
        );
        CoffeeItem cafe1Espresso = new CoffeeItem(
                "Espresso", 2.99, "espresso only", 123123, "cafe1"
        );
        cafe1.put("Latte", cafe1Latte);
        cafe1.put("Espresso", cafe1Espresso);
        coffeeMenu.put("Cafe1", cafe1);

        Map<String, CoffeeItem> cafe2 = new HashMap<>();
        CoffeeItem cafe2Latte = new CoffeeItem(
                "Caramel latte", 2.99, "espresso and milk", 123123, "cafe2"
        );
        cafe2.put("Caramel Latte", cafe2Latte);
        coffeeMenu.put("Cafe2", cafe2);

        User testUser = new User();
        testUser.setUsername("testUser");

        Order order = new Order();
        order.setCustomer(testUser);
        order.setStatus(OrderStatus.NEW);
        order.getOrderTime();
        order.setItems(List.of("Latte"));
        order.setShopId(1);

        // mocking triggers
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(testUser));
        when(coffeeController.getMenuForShop("cafe1")).thenReturn(cafe1);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        try {
            OrderDTO result = orderService.placeOrder(order);

            assertNotNull(result);
            assertEquals("testUser", result.getCustomerName());
            assertEquals("Latte", result.getItems().get(0));
            ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
            verify(orderRepository).save(orderCaptor.capture());

            System.out.println("=".repeat(50));
            System.out.println("Trying to retrieve shop: " + "cafe" + (int) order.getShopId());
            System.out.println("RESULT: "+ result);
            System.out.println("=".repeat(50));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}