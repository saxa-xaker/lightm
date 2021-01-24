package ru.rcaltd.lightm.services;

import org.springframework.stereotype.Service;
import ru.rcaltd.lightm.entities.SensorMonitor;
import ru.rcaltd.lightm.services.ultraSoundSensorService.*;

@Service
public class MonitorService {

    final SensorMonitor sensorMonitor = new SensorMonitor();
    final USSS1 usss1;
    final USSS2 usss2;
    final USSS3 usss3;
    final USSS4 usss4;
    final USSS5 usss5;
    final USSS6 usss6;

    public MonitorService(USSS1 usss1, USSS2 usss2, USSS3 usss3, USSS4 usss4, USSS5 usss5, USSS6 usss6) {
        this.usss1 = usss1;
        this.usss2 = usss2;
        this.usss3 = usss3;
        this.usss4 = usss4;
        this.usss5 = usss5;
        this.usss6 = usss6;
    }

    public void monitorsStart() {
        final Thread thread1 = new Thread(() -> {
            try {
                usss1.monitorStart();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Thread thread2 = new Thread(() -> {
            try {
                usss2.monitorStart();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Thread thread3 = new Thread(() -> {
            try {
                usss3.monitorStart();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Thread thread4 = new Thread(() -> {
            try {
                usss4.monitorStart();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Thread thread5 = new Thread(() -> {
            try {
                usss5.monitorStart();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Thread thread6 = new Thread(() -> {
            try {
                usss6.monitorStart();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }

//    public void mainCycle() {
//        if (sensorMonitor.isSensor1() ==)
//    }

}
