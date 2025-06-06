package edu.depaul.coffeeapp.shop;

import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                "Caramel latte", 2.99, "espresso and milk", 123123, "cafe2"
        );
        cafe2.put("Caramel Latte", cafe2Latte);
        coffeeMenu.put("cafe2", cafe2);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<CoffeeItem>> searchCoffee(@RequestParam String name) {
//        String lowerCaseName = name.toLowerCase();
//        List<CoffeeItem> results = new ArrayList<>();
//        for (Map<String, CoffeeItem> shopMenu : coffeeMenu.values()) {
//            for (CoffeeItem coffeeItem : shopMenu.values()) {
//                if (coffeeItem.getName().toLowerCase().contains(lowerCaseName)) {
//                    results.add(coffeeItem);
//                }
//            }
//        }
//        return ResponseEntity.ok(results);
//
//    }
    @GetMapping("/search")
    public List<CoffeeItem> searchCoffee(
            @RequestParam("query") String name,
            @RequestParam("shop") String shopId
    ) {
        Map<String, CoffeeItem> menu = getMenuForShop(shopId);
        return menu.values().stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Map<String, CoffeeItem> getMenuForShop(String shopId) {
        return coffeeMenu.getOrDefault(shopId, new HashMap<>());
    }

    public Map<String, Map<String, CoffeeItem>> getAllMenus() {
        return coffeeMenu;
    }
}
