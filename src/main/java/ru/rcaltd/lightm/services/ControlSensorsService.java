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

    //Main idle position
    public void systemStartIdle() {
        if (!isStop)
            ultraSoundSensorService1.monitorStart();
        ultraSoundSensorService2.monitorStop();
        ultraSoundSensorService3.monitorStop();
        ultraSoundSensorService4.monitorStop();
        ultraSoundSensorService5.monitorStop();
        if (!isStop)
            ultraSoundSensorService6.monitorStart();
    }

    //First position
    public void systemStart1() {
        ultraSoundSensorService1.monitorStop(); //person here
        if (!isStop)
            ultraSoundSensorService2.monitorStart();
        ultraSoundSensorService3.monitorStop();
        ultraSoundSensorService4.monitorStop();
        ultraSoundSensorService5.monitorStop();
        ultraSoundSensorService6.monitorStop();
    }

    //Second position
    public void systemStart2() {
        if (!isStop)
            ultraSoundSensorService1.monitorStart();
        ultraSoundSensorService2.monitorStop(); //person here
        if (!isStop)
            ultraSoundSensorService3.monitorStart();
        ultraSoundSensorService4.monitorStop();
        ultraSoundSensorService5.monitorStop();
        ultraSoundSensorService6.monitorStop();
    }

    //Third position
    public void systemStart3() {
        ultraSoundSensorService1.monitorStop();
        if (!isStop)
            ultraSoundSensorService2.monitorStart();
        ultraSoundSensorService3.monitorStop(); //person here
        if (!isStop)
            ultraSoundSensorService4.monitorStart();
        ultraSoundSensorService5.monitorStop();
        ultraSoundSensorService6.monitorStop();
    }

    //Fourth position
    public void systemStart4() {
        ultraSoundSensorService1.monitorStop();
        ultraSoundSensorService2.monitorStop();
        if (!isStop)
            ultraSoundSensorService3.monitorStart();
        ultraSoundSensorService4.monitorStop(); //person here
        if (!isStop)
            ultraSoundSensorService5.monitorStart();
        ultraSoundSensorService6.monitorStop();
    }

    //Fifth position
    public void systemStart5() {
        ultraSoundSensorService1.monitorStop();
        ultraSoundSensorService2.monitorStop();
        ultraSoundSensorService3.monitorStop();
        if (!isStop)
            ultraSoundSensorService4.monitorStart();
        ultraSoundSensorService5.monitorStop(); //person here
        if (!isStop)
            ultraSoundSensorService6.monitorStart();
    }

    //Sixth position
    public void systemStart6() {
        ultraSoundSensorService1.monitorStop();
        ultraSoundSensorService2.monitorStop();
        ultraSoundSensorService3.monitorStop();
        ultraSoundSensorService4.monitorStop();
        if (!isStop)
            ultraSoundSensorService5.monitorStart();
        ultraSoundSensorService6.monitorStop(); //person here
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
    }
}