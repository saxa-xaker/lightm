package ru.rcaltd.lightm.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.rcaltd.lightm.services.relayService.*;
import ru.rcaltd.lightm.services.ultraSoundSensorService.*;

@Service
public class MainCycleService {

    final ControlSensorsService controlSensorsService;
    final
    UltraSoundSensorService1 ultraSoundSensorService1;
    final
    UltraSoundSensorService2 ultraSoundSensorService2;
    final
    UltraSoundSensorService3 ultraSoundSensorService3;
    final
    UltraSoundSensorService4 ultraSoundSensorService4;
    final
    UltraSoundSensorService5 ultraSoundSensorService5;
    final
    UltraSoundSensorService6 ultraSoundSensorService6;
    final RelayService1 relayService1;
    final RelayService2 relayService2;
    final RelayService3 relayService3;
    final RelayService4 relayService4;
    final RelayService5 relayService5;
    final RelayService6 relayService6;
    private boolean isStop = false;
    boolean isStartedIdle = false;
    boolean isStarted1 = false;
    boolean isStarted2 = false;
//    boolean isStarted3 = false;
//    boolean isStarted4 = false;
//    boolean isStarted5 = false;
//    boolean isStarted6 = false;

    public MainCycleService(ControlSensorsService controlSensorsService, UltraSoundSensorService1 ultraSoundSensorService1,
                            UltraSoundSensorService2 ultraSoundSensorService2,
                            UltraSoundSensorService3 ultraSoundSensorService3,
                            UltraSoundSensorService4 ultraSoundSensorService4,
                            UltraSoundSensorService5 ultraSoundSensorService5,
                            UltraSoundSensorService6 ultraSoundSensorService6, RelayService1 relayService1, RelayService2 relayService2, RelayService3 relayService3, RelayService4 relayService4, RelayService5 relayService5, RelayService6 relayService6) {
        this.controlSensorsService = controlSensorsService;
        this.ultraSoundSensorService1 = ultraSoundSensorService1;
        this.ultraSoundSensorService2 = ultraSoundSensorService2;
        this.ultraSoundSensorService3 = ultraSoundSensorService3;
        this.ultraSoundSensorService4 = ultraSoundSensorService4;
        this.ultraSoundSensorService5 = ultraSoundSensorService5;
        this.ultraSoundSensorService6 = ultraSoundSensorService6;
        this.relayService1 = relayService1;
        this.relayService2 = relayService2;
        this.relayService3 = relayService3;
        this.relayService4 = relayService4;
        this.relayService5 = relayService5;
        this.relayService6 = relayService6;
    }

    @Async
    public void mainCycleStart() throws InterruptedException {
        isStop = false;
        ultraSoundSensorService1.monitorStart();
        ultraSoundSensorService2.monitorStart();
        System.out.println("monitor -ALL- Started");

        while (!isStop) {
            if (ultraSoundSensorService1.getState().equals("HIGH")) {
                if (!isStartedIdle) {
                    relayService1.relayOn();
                    relayService2.relayOff();
                    isStartedIdle = true;
                    isStarted1 = false;
                }
                if (!isStarted1) {
                    System.out.println("1 <- You are here");
                    System.out.println("2");
                    System.out.println("3");
                    System.out.println("4");
                    System.out.println("5");
                    System.out.println("6");
                    relayService1.relayOn();
                    relayService2.relayOff();
                    isStartedIdle = false;
                    isStarted1 = true;
                    isStarted2 = false;
                }

                Thread.sleep(1500);
            }

            if (ultraSoundSensorService2.getState().equals("HIGH")) {
                if (!isStarted2) {
                    System.out.println("1");
                    System.out.println("2 <- You are here");
                    System.out.println("3");
                    System.out.println("4");
                    System.out.println("5");
                    System.out.println("6");
                    relayService1.relayOff();
                    relayService2.relayOn();
                    relayService3.relayOff();
                    isStartedIdle = false;
                    isStarted1 = false;
                    isStarted2 = true;
                }
                Thread.sleep(1500);
            }
        }
    }

    public void mainCycleStop() {
        isStop = true;
        controlSensorsService.systemStop();
    }
}