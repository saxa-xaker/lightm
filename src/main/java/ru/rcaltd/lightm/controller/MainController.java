package ru.rcaltd.lightm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rcaltd.lightm.services.MainCycle;
import ru.rcaltd.lightm.services.ultraSoundSensorService.USSS1;
import ru.rcaltd.lightm.services.ultraSoundSensorService.USSS2;
import ru.rcaltd.lightm.services.ultraSoundSensorService.USSS3;

@Controller
public class MainController {

    final USSS1 USSS1;
    final USSS2 USSS2;
    final USSS3 USSS3;
    MainCycle mainCycle;

    public MainController(MainCycle mainCycle, USSS1 USSS1, USSS2 USSS2, USSS3 USSS3) {
        this.mainCycle = mainCycle;
        this.USSS1 = USSS1;
        this.USSS2 = USSS2;
        this.USSS3 = USSS3;
    }

    @GetMapping("/")
    public String mainPage() {

        return "mainpage";
    }

    @GetMapping("/mainCycleStart")
    public String mainCycleStart() throws InterruptedException {
        final Thread thread1 = new Thread(() -> {
            try {
                USSS1.monitorStart();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Thread thread2 = new Thread(() -> {
            try {
                USSS2.monitorStart();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
//        USSS3.monitorStart();
        //       mainCycle.mainCycleStart();
        System.out.println("System started");
        return "mainpage";
    }

    @GetMapping("/mainCycleStop")
    public String mainCycleStop() throws InterruptedException {
        mainCycle.mainCycleStop();
        System.out.println("System stopped");
        return "mainpage";
    }

    @GetMapping("/mainCycleRestart")
    public String mainCycleRestart() throws InterruptedException {
        mainCycle.mainCycleStop();
        mainCycle.mainCycleStart();
        System.out.println("Main cycle restarted");
        return "mainpage";
    }
}
