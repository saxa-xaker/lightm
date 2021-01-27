package ru.rcaltd.lightm.services;

import org.springframework.stereotype.Service;
import ru.rcaltd.lightm.entities.SensorMonitor;
import ru.rcaltd.lightm.services.relayService.*;
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
    final RS1 rs1;
    final RS2 rs2;
    final RS3 rs3;
    final RS4 rs4;
    final RS5 rs5;
    final RS6 rs6;
    private boolean isStopped;

    public MonitorService(USSS1 usss1, USSS2 usss2, USSS3 usss3, USSS4 usss4, USSS5 usss5, USSS6 usss6, RS1 rs1, RS2 rs2, RS3 rs3, RS4 rs4, RS5 rs5, RS6 rs6) {
        this.usss1 = usss1;
        this.usss2 = usss2;
        this.usss3 = usss3;
        this.usss4 = usss4;
        this.usss5 = usss5;
        this.usss6 = usss6;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.rs3 = rs3;
        this.rs4 = rs4;
        this.rs5 = rs5;
        this.rs6 = rs6;
    }

    public void monitorsStart() {

        isStopped = false;

        final Thread thread1 = new Thread(() -> {
            try {
                usss1.monitorStart(sensorMonitor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Thread thread2 = new Thread(() -> {
            try {
                usss2.monitorStart(sensorMonitor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Thread thread3 = new Thread(() -> {
            try {
                usss3.monitorStart(sensorMonitor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Thread thread4 = new Thread(() -> {
            try {
                usss4.monitorStart(sensorMonitor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Thread thread5 = new Thread(() -> {
            try {
                usss5.monitorStart(sensorMonitor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Thread thread6 = new Thread(() -> {
            try {
                usss6.monitorStart(sensorMonitor);
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

        //IDLE MODE
        sensorMonitor.setActiveSensor1(true);
        sensorMonitor.setActiveSensor2(false);
        sensorMonitor.setActiveSensor3(false);
        sensorMonitor.setActiveSensor4(false);
        sensorMonitor.setActiveSensor5(false);
        sensorMonitor.setActiveSensor6(true);
        System.out.println("INIT DONE");

    }

    public void monitorStop() {
        System.exit(0);
    }

}
