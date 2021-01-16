package ru.rcaltd.lightm.services.ultraSoundSensorService;

import org.springframework.scheduling.annotation.Async;
import ru.rcaltd.lightm.services.ControlSensorsService;
import ru.rcaltd.lightm.services.relayService.*;

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
    public void mainCycleStart() {
        controlSensorsService.systemStartIdle();
        isStop = false;
        while (!isStop) {
            if (ultraSoundSensorService1.getState().equals("HIGH")) {
                relayService1.relayOn();
                relayService2.relayOff();
                controlSensorsService.systemStart1();
                System.out.println("1 <- You are here");
                System.out.println("2");
                System.out.println("3");
                System.out.println("4");
                System.out.println("5");
                System.out.println("6");
            }

            if (ultraSoundSensorService2.getState().equals("HIGH")) {
                relayService1.relayOff();
                relayService2.relayOn();
                relayService3.relayOff();
                controlSensorsService.systemStart2();
                System.out.println("1");
                System.out.println("2 <- You are here");
                System.out.println("3");
                System.out.println("4");
                System.out.println("5");
                System.out.println("6");
            }

            if (ultraSoundSensorService3.getState().equals("HIGH")) {
                relayService2.relayOff();
                relayService3.relayOn();
                relayService4.relayOff();
                controlSensorsService.systemStart3();
                System.out.println("1");
                System.out.println("2");
                System.out.println("3 <- You are here");
                System.out.println("4");
                System.out.println("5");
                System.out.println("6");
            }

            if (ultraSoundSensorService4.getState().equals("HIGH")) {
                relayService3.relayOff();
                relayService4.relayOn();
                relayService5.relayOff();
                controlSensorsService.systemStart4();
                System.out.println("1");
                System.out.println("2");
                System.out.println("3");
                System.out.println("4 <- You are here");
                System.out.println("5");
                System.out.println("6");
            }

            if (ultraSoundSensorService5.getState().equals("HIGH")) {
                relayService4.relayOff();
                relayService5.relayOn();
                relayService6.relayOff();
                controlSensorsService.systemStart5();
                System.out.println("1");
                System.out.println("2");
                System.out.println("3");
                System.out.println("4");
                System.out.println("5 <- You are here");
                System.out.println("6");
            }

            if (ultraSoundSensorService6.getState().equals("HIGH")) {
                relayService5.relayOff();
                relayService6.relayOn();
                controlSensorsService.systemStart6();
                System.out.println("1");
                System.out.println("2");
                System.out.println("3");
                System.out.println("4");
                System.out.println("5");
                System.out.println("6 <- You are here");
            }
        }
    }

    public void mainCycleStop() {
        isStop = true;
        controlSensorsService.systemStop();
    }
}