package ru.rcaltd.lightm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rcaltd.lightm.services.MainCycleService;

@Controller
public class MainController {

    final MainCycleService mainCycleService;

    public MainController(MainCycleService mainCycleService) {
        this.mainCycleService = mainCycleService;
    }

    @GetMapping("/")
    public String mainPage() {

        return "mainpage";
    }

    @GetMapping("/mainCycleStart")
    public String mainCycleStart() {
        mainCycleService.mainCycleStart();
        System.out.println("System started");
        return "mainpage";
    }

    @GetMapping("/mainCycleStop")
    public String mainCycleStop() {
        mainCycleService.mainCycleStop();
        System.out.println("System stopped");
        return "mainpage";
    }
}
