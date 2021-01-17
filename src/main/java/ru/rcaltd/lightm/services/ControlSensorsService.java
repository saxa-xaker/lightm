package ru.rcaltd.lightm.services;

import org.springframework.stereotype.Service;
import ru.rcaltd.lightm.services.relayService.*;
import ru.rcaltd.lightm.services.ultraSoundSensorService.*;

@Service
public class ControlSensorsService {

    final RelayService1 relayService1;
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
    final RelayService2 relayService2;
    final RelayService3 relayService3;
    final RelayService4 relayService4;
    final RelayService5 relayService5;
    final RelayService6 relayService6;
    private boolean isStop = false;


    public ControlSensorsService(UltraSoundSensorService1 ultraSoundSensorService1,
                                 UltraSoundSensorService2 ultraSoundSensorService2,
                                 UltraSoundSensorService3 ultraSoundSensorService3,
                                 UltraSoundSensorService4 ultraSoundSensorService4,
                                 UltraSoundSensorService5 ultraSoundSensorService5,
                                 UltraSoundSensorService6 ultraSoundSensorService6, RelayService1 relayService1, RelayService2 relayService2, RelayService3 relayService3, RelayService4 relayService4, RelayService5 relayService5, RelayService6 relayService6) {
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

    public void systemStop() {
        isStop = true;
        ultraSoundSensorService1.monitorStop();
        ultraSoundSensorService2.monitorStop();
        ultraSoundSensorService3.monitorStop();
        ultraSoundSensorService4.monitorStop();
        ultraSoundSensorService5.monitorStop();
        ultraSoundSensorService6.monitorStop();
        relayService1.relayOff();
        relayService2.relayOff();
        relayService3.relayOff();
        relayService4.relayOff();
        relayService5.relayOff();
        relayService6.relayOff();
        System.out.println("-ALL- Stopped");
    }
}