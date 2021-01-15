package ru.rcaltd.lightm.services;

import org.springframework.stereotype.Service;
import ru.rcaltd.lightm.entities.UltraSoundSensorState;
import ru.rcaltd.lightm.services.ultraSoundSensorService.*;

@Service
public class ControlSensorsService {

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

    public ControlSensorsService(UltraSoundSensorService1 ultraSoundSensorService1,
                                 UltraSoundSensorService2 ultraSoundSensorService2,
                                 UltraSoundSensorService3 ultraSoundSensorService3,
                                 UltraSoundSensorService4 ultraSoundSensorService4,
                                 UltraSoundSensorService5 ultraSoundSensorService5,
                                 UltraSoundSensorService6 ultraSoundSensorService6) {
        this.ultraSoundSensorService1 = ultraSoundSensorService1;
        this.ultraSoundSensorService2 = ultraSoundSensorService2;
        this.ultraSoundSensorService3 = ultraSoundSensorService3;
        this.ultraSoundSensorService4 = ultraSoundSensorService4;
        this.ultraSoundSensorService5 = ultraSoundSensorService5;
        this.ultraSoundSensorService6 = ultraSoundSensorService6;
    }

    public void systemStart() {
        ultraSoundSensorService1.monitorStart();
        ultraSoundSensorService2.monitorStart();
        ultraSoundSensorService3.monitorStart();
        ultraSoundSensorService4.monitorStart();
        ultraSoundSensorService5.monitorStart();
        ultraSoundSensorService6.monitorStart();

    }

    public void systemStop() {
        ultraSoundSensorService1.monitorStop();
        ultraSoundSensorService2.monitorStop();
        ultraSoundSensorService3.monitorStop();
        ultraSoundSensorService4.monitorStop();
        ultraSoundSensorService5.monitorStop();
        ultraSoundSensorService6.monitorStop();
    }

    public UltraSoundSensorState ultraSoundSensorState() {
        UltraSoundSensorState ultraSoundSensorState = new UltraSoundSensorState();
        ultraSoundSensorState.setUltraSoundSensorState1(ultraSoundSensorService1.getState());
        ultraSoundSensorState.setUltraSoundSensorState1(ultraSoundSensorService2.getState());
        ultraSoundSensorState.setUltraSoundSensorState1(ultraSoundSensorService3.getState());
        ultraSoundSensorState.setUltraSoundSensorState1(ultraSoundSensorService4.getState());
        ultraSoundSensorState.setUltraSoundSensorState1(ultraSoundSensorService5.getState());
        ultraSoundSensorState.setUltraSoundSensorState1(ultraSoundSensorService6.getState());
        return ultraSoundSensorState;
    }
}
