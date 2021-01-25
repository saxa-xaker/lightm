package ru.rcaltd.lightm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rcaltd.lightm.services.MonitorService;

@Controller
public class MainController {

    final MonitorService monitorService;

    public MainController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @GetMapping("/")
    public String mainPage() {

        return "mainpage";
    }

    @GetMapping("/mainCycleStart")
    public String mainCycleStart() {
        monitorService.monitorsStart();
        System.out.println("System started");
        return "mainpage";
    }

    @GetMapping("/mainCycleStop")
    public String mainCycleStop() {
        monitorService.monitorStop();
        System.out.println("System stopped");
        return "mainpage";
    }
}
