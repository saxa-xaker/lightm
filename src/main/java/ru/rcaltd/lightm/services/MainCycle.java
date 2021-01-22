package ru.rcaltd.lightm.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.rcaltd.lightm.services.relayService.RS1;
import ru.rcaltd.lightm.services.relayService.RS2;
import ru.rcaltd.lightm.services.relayService.RS3;
import ru.rcaltd.lightm.services.ultraSoundSensorService.USSS1;
import ru.rcaltd.lightm.services.ultraSoundSensorService.USSS2;
import ru.rcaltd.lightm.services.ultraSoundSensorService.USSS3;

@Service
public class MainCycle {

    final USSS1 USSS1;
    final USSS2 USSS2;
    final USSS3 USSS3;
    final RS1 RS1;
    final RS2 RS2;
    final RS3 RS3;

    private boolean isStopped = false;

    public MainCycle(USSS1 USSS1, USSS2 USSS2, USSS3 USSS3, RS1 RS1, RS2 RS2, RS3 RS3) {
        this.USSS1 = USSS1;
        this.USSS2 = USSS2;
        this.USSS3 = USSS3;
        this.RS1 = RS1;
        this.RS2 = RS2;
        this.RS3 = RS3;
    }

    @Async
    public void mainCycleStart() throws InterruptedException {
        isStopped = false;
        RS1.relayOff();
        RS2.relayOff();
        System.out.println("monitor -ALL- Started");

//        while (!isStopped) {

//            if (USSS1.getState()) {
//                if (!RS1.getState()) {
//                    RS1.relayOn();
// //                   Thread.sleep(300);
//                }
//            } else {
//                if (RS1.getState()) {
// //                   Thread.sleep(200);
//                    RS1.relayOff();
//                }
//            }
//            if (USSS2.getState()) {
//                if (!RS2.getState()) {
//                    RS2.relayOn();
////                    Thread.sleep(100);
//                }
//            } else {
//                if (RS2.getState()) {
// //                   Thread.sleep(300);
//                    RS2.relayOff();
//                }
//            }
//            if (USSS3.getState()) {
//                if (!RS3.getState()) {
//                    RS3.relayOn();
////                    Thread.sleep(300);
//                }
//            } else {
//                if (RS3.getState()) {
////                    Thread.sleep(300);
//                    RS3.relayOff();
//                }
//            }
    }
    //   }

    public void mainCycleStop() {
        isStopped = true;
    }
}