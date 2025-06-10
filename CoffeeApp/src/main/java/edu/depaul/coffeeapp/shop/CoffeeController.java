package edu.depaul.coffeeapp.shop;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/coffee")
/**
 * The coffee controller is responsible for initializing the cafe menu.
 *
 * All coffee items should be placed into a Map(String, CoffeeItem) object, which will be collectively added to
 * a Map(String, Map(String, CoffeeItem)) object.
 */
public class CoffeeController {
    private OrderService orderService = new OrderService();
    private Map<String, Map<String, CoffeeItem>> coffeeMenu = new HashMap<>();

    @PostConstruct
    public void init() {
        // create a new hashmap for each different shop and 'put' coffee items into the menu
        Map<String, CoffeeItem> cafe1 = new HashMap<>();

        CoffeeItem cafe1Latte = new CoffeeItem(
                "Latte", 2.99, "espresso and milk", 123123, "cafe1"
        );
        CoffeeItem cafe1Espresso = new CoffeeItem(
                "Espresso", 2.99, "espresso only", 123123, "cafe1"
        );
        cafe1.put("Latte", cafe1Latte);
        cafe1.put("Espresso", cafe1Espresso);
        coffeeMenu.put("cafe1", cafe1);

        Map<String, CoffeeItem> cafe2 = new HashMap<>();
        CoffeeItem cafe2Latte = new CoffeeItem(
                "Caramel latte", 2.99, "Espresso and milk", 111111, "cafe2"
        );
        CoffeeItem cafe2Matcha = new CoffeeItem(
                "Matcha latte", 2.99, "Matcha and milk", 111111, "cafe2"
        );
        CoffeeItem cafe2Banana = new CoffeeItem(
                "Banana oatmilk latte", 2.99, "Espresso, banana extract and oat milk", 111111, "cafe2"
        );
        cafe2.put("Caramel Latte", cafe2Latte);
        cafe2.put("Matcha Latte", cafe2Matcha);
        cafe2.put("Banana Latte", cafe2Banana);
        coffeeMenu.put("cafe2", cafe2);
    }

    @GetMapping("/search")
    public Map<String, List<CoffeeItem>> searchCoffee(
            @RequestParam("query") String name,
            @RequestParam("shop") String shopId
    ) {

        String lowerCaseName = name.toLowerCase();
        Map<String, CoffeeItem> currentMenu = getMenuForShop(shopId);

        List<CoffeeItem> availableHere = currentMenu.values().stream()
                .filter(item -> item.getName().toLowerCase().contains(lowerCaseName))
                .collect(Collectors.toList());

        List<CoffeeItem> availableElsewhere = new ArrayList<>();
        if (availableHere.isEmpty()) {
            for (Map.Entry<String, Map<String, CoffeeItem>> entry : coffeeMenu.entrySet()) {
                String otherShopId = entry.getKey();
                if (!otherShopId.equalsIgnoreCase(shopId)) {
                    availableElsewhere.addAll(
                            entry.getValue().values().stream()
                                    .filter(item -> item.getName().toLowerCase().contains(lowerCaseName))
                                    .toList()
                    );
                }
            }
        }

        Map<String, List<CoffeeItem>> result = new HashMap<>();
        result.put("here", availableHere);
        result.put("elsewhere", availableElsewhere);
        return result;
    }

    @PostMapping("/orders/checkout")
    public ResponseEntity<OrderDTO> checkout(@RequestBody Order request) throws Exception {
        OrderDTO receipt = orderService.placeOrder(request);
        return ResponseEntity.ok(receipt);
    }


    public Map<String, CoffeeItem> getMenuForShop(String shopId) {
        return coffeeMenu.getOrDefault(shopId, new HashMap<>());
    }

    public Map<String, Map<String, CoffeeItem>> getAllMenus() {
        return coffeeMenu;
    }

}
