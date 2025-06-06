package edu.depaul.coffeeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @GetMapping("/receipt")
    public String receipt() {
        return "receipt";
    }
}
