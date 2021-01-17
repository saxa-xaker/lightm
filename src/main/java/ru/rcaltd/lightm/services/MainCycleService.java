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
    boolean isStarted3 = false;
    boolean isStartedIdle = false;
    boolean isStarted1 = false;
    boolean isStarted2 = false;
    boolean isStarted4 = false;
    boolean isStarted5 = false;
    boolean isStarted6 = false;
    private boolean isStopped = false;

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
        int counter1 = 0;
        int counter2 = 0;
        isStopped = false;
        isStartedIdle = true;
        ultraSoundSensorService1.monitorStart();
        ultraSoundSensorService2.monitorStart();
        relayService1.relayOff();
        relayService2.relayOff();

        System.out.println("monitor -ALL- Started");

        while (!isStopped) {

            if (isStartedIdle) {
                relayService1.relayOff();
                relayService2.relayOff();
                System.out.println("System idle Started");
                System.out.println("1");
                System.out.println("2");
                System.out.println("3");
                System.out.println("4");
                System.out.println("5");
                System.out.println("6");
                while (ultraSoundSensorService1.getState().equals("LOW")
                        //                     &&  ultraSoundSensorService6.getState().equals("LOW")
                        && !isStopped) {
                    System.out.println("System IDLE");
                    Thread.sleep(500);
                }
                // Sure, person stay at first sensor yet
                if (ultraSoundSensorService1.getState().equals("HIGH")) {
                    isStarted1 = true;
                    isStartedIdle = false;
                }
//                if (ultraSoundSensorService6.getState().equals("HIGH")) {
//                    isStarted6 = true;
//                }
                Thread.sleep(100);
            }

            if (isStarted1) {
                relayService1.relayOn();
                relayService2.relayOff();
                System.out.println("1 <- You are here");
                System.out.println("2");
                System.out.println("3");
                System.out.println("4");
                System.out.println("5");
                System.out.println("6");
                isStartedIdle = false;
                isStarted1 = true;
                isStarted2 = false;

                if (ultraSoundSensorService1.getState().equals("HIGH")) {
                    while (ultraSoundSensorService1.getState().equals("HIGH")
                            && !isStopped) {
                        System.out.println("System waiting for the LOW signal from first sensor");
                        Thread.sleep(100);
                    }
                    while (ultraSoundSensorService1.getState().equals("LOW")
                            && !isStopped) {
                        while (ultraSoundSensorService1.getState().equals("LOW") &&
                                ultraSoundSensorService2.getState().equals("LOW") && counter1 < 10
                                && !isStopped) {
                            counter1++;
                            Thread.sleep(100);

                        }
//                    System.out.println("System waiting for the signal from first or second sensor");
                        break;
                    }
                    isStarted1 = false;
                    if (ultraSoundSensorService1.getState().equals("LOW") && ultraSoundSensorService2.getState().equals("LOW")) {
                        isStarted1 = false;
                        isStarted2 = false;
                        isStartedIdle = true;
                    }
                    if (ultraSoundSensorService1.getState().equals("HIGH") && ultraSoundSensorService2.getState().equals("LOW")) {
                        isStarted1 = true;
                    }
                    if (ultraSoundSensorService1.getState().equals("LOW") && ultraSoundSensorService2.getState().equals("HIGH")) {
                        isStarted2 = true;
                    }
                }
                counter1 = 0;
                Thread.sleep(100);
            }

            if (isStarted2) {
                relayService1.relayOff();
                relayService2.relayOn();
                System.out.println("1");
                System.out.println("2 <- You are here");
                System.out.println("3");
                System.out.println("4");
                System.out.println("5");
                System.out.println("6");
                isStartedIdle = false;
                isStarted1 = false;
                isStarted2 = true;

                if (ultraSoundSensorService2.getState().equals("HIGH")) {
                    while (ultraSoundSensorService2.getState().equals("HIGH")
                            && !isStopped) {
                        System.out.println("System waiting for the LOW signal from second sensor");
                        Thread.sleep(100);
                    }
                    while (ultraSoundSensorService2.getState().equals("LOW")
                            && !isStopped) {
                        while (ultraSoundSensorService2.getState().equals("LOW") &&
                                ultraSoundSensorService1.getState().equals("LOW") && counter2 < 10
                                && !isStopped) {
                            counter2++;
                            Thread.sleep(100);

                        }
//                    System.out.println("System waiting for the signal from first or second sensor");
                        break;
                    }
                    isStarted2 = false;
                    if (ultraSoundSensorService2.getState().equals("LOW") && ultraSoundSensorService1.getState().equals("LOW")) {
                        isStarted1 = false;
                        isStarted2 = false;
                        isStartedIdle = true;
                    }
                    if (ultraSoundSensorService1.getState().equals("HIGH") && ultraSoundSensorService2.getState().equals("LOW")) {
                        isStarted1 = true;
                    }
                    if (ultraSoundSensorService1.getState().equals("LOW") && ultraSoundSensorService2.getState().equals("HIGH")) {
                        isStarted2 = true;
                    }
                }
                counter2 = 0;
                Thread.sleep(100);
            }
        }
    }
    public void mainCycleStop() {
        isStopped = true;
        controlSensorsService.systemStop();
    }
}