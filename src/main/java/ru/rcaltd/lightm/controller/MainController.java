package ru.rcaltd.lightm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rcaltd.lightm.services.MainCycleService;
import ru.rcaltd.lightm.services.relayService.RelayService1;

@Controller
public class MainController {

    final MainCycleService mainCycleService;
    final RelayService1 relayService1;

    public MainController(MainCycleService mainCycleService, RelayService1 relayService1) {
        this.mainCycleService = mainCycleService;
        this.relayService1 = relayService1;
    }

    @GetMapping("/")
    public String mainPage() {

        return "mainpage";
    }

    @GetMapping("/mainCycleStart")
    public String mainCycleStart() throws InterruptedException {
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

    @GetMapping("/relay1On")
    public String relay1On() {
        relayService1.relayOn();
        System.out.println("relay1Start");
        return "mainpage";
    }

    @GetMapping("/relay1Off")
    public String relay1Off() {
        relayService1.relayOff();
        System.out.println("relay1Off");
        return "mainpage";
    }
}
