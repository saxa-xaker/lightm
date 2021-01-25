package ru.rcaltd.lightm.services;

import org.springframework.stereotype.Service;
import ru.rcaltd.lightm.entities.SensorMonitor;
import ru.rcaltd.lightm.services.relayService.RS1;
import ru.rcaltd.lightm.services.relayService.RS2;
import ru.rcaltd.lightm.services.relayService.RS3;
import ru.rcaltd.lightm.services.ultraSoundSensorService.*;

@Service
public class MonitorService {

    final RS1 rs1;
    final SensorMonitor sensorMonitor = new SensorMonitor();
    final USSS1 usss1;
    final USSS2 usss2;
    final USSS3 usss3;
    final USSS4 usss4;
    final USSS5 usss5;
    final USSS6 usss6;
    final RS2 rs2;
    final RS3 rs3;
    private boolean isStopped;

    public MonitorService(USSS1 usss1, USSS2 usss2, USSS3 usss3, USSS4 usss4, USSS5 usss5, USSS6 usss6, RS1 rs1, RS2 rs2, RS3 rs3) {
        this.usss1 = usss1;
        this.usss2 = usss2;
        this.usss3 = usss3;
        this.usss4 = usss4;
        this.usss5 = usss5;
        this.usss6 = usss6;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.rs3 = rs3;
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
//        final Thread thread3 = new Thread(() -> {
//            try {
//                usss3.monitorStart(sensorMonitor);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        final Thread thread4 = new Thread(() -> {
//            try {
//                usss4.monitorStart();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        final Thread thread5 = new Thread(() -> {
//            try {
//                usss5.monitorStart();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        final Thread thread6 = new Thread(() -> {
//            try {
//                usss6.monitorStart();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

        thread1.start();
        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();
//        thread6.start();

        //IDLE MODE
        sensorMonitor.setActiveSensor1(true);
        sensorMonitor.setActiveSensor2(false);
        sensorMonitor.setActiveSensor3(false);
        sensorMonitor.setActiveSensor4(false);
        sensorMonitor.setActiveSensor5(false);
        sensorMonitor.setActiveSensor6(true);
        sensorMonitor.setBlocked(false);
        sensorMonitor.setWhoBlocked(0);
        while (!isStopped) {
            //Waiting only for first or third sensor is ON
            switch (sensorMonitor.getWhoBlocked()) {
                case 1: {
                    if (sensorMonitor.isSensorOn1()) {
                        if (!rs1.getState()) rs1.relayOn();
                        sensorMonitor.setActiveSensor1(false);
                        sensorMonitor.setActiveSensor2(true);
                        sensorMonitor.setActiveSensor3(false);
                        sensorMonitor.setActiveSensor4(false);
                        sensorMonitor.setActiveSensor5(false);
                        sensorMonitor.setActiveSensor6(true);
                        sensorMonitor.setBlocked(false);
                    } else {
                        if (rs1.getState()) rs1.relayOff();
                        sensorMonitor.setActiveSensor1(true);
                        sensorMonitor.setActiveSensor2(true);
                        sensorMonitor.setActiveSensor3(false);
                        sensorMonitor.setActiveSensor4(false);
                        sensorMonitor.setActiveSensor5(false);
                        sensorMonitor.setActiveSensor6(true);
                        sensorMonitor.setBlocked(false);
                    }
                    break;
                }
                case 2: {
                    if (sensorMonitor.isSensorOn2()) {
                        sensorMonitor.setActiveSensor1(true);
                        sensorMonitor.setActiveSensor2(false);
                        sensorMonitor.setActiveSensor3(true);
                        sensorMonitor.setActiveSensor4(false);
                        sensorMonitor.setActiveSensor5(false);
                        sensorMonitor.setActiveSensor6(false);
                        if (!rs2.getState()) rs2.relayOn();
                        sensorMonitor.setBlocked(false);
                    } else {
                        if (rs2.getState()) rs2.relayOff();
                        sensorMonitor.setActiveSensor1(true);
                        sensorMonitor.setActiveSensor2(true);
                        sensorMonitor.setActiveSensor3(true);
                        sensorMonitor.setActiveSensor4(false);
                        sensorMonitor.setActiveSensor5(false);
                        sensorMonitor.setActiveSensor6(false);
                        sensorMonitor.setBlocked(false);

                    }
                    break;
                }
                case 3: {
//                    if (sensorMonitor.isSensorOn3()) {
//                        try {
//                            if (!rs3.getState()) {
//                                rs3.relayOn();
//                            }
//                            sensorMonitor.setActiveSensor1(false);
//                            sensorMonitor.setActiveSensor2(true);
//                            sensorMonitor.setActiveSensor3(false);
//                            sensorMonitor.setActiveSensor4(true);
//                            sensorMonitor.setActiveSensor5(false);
//                            sensorMonitor.setActiveSensor6(false);
//                            sensorMonitor.setBlocked(false);
//                        } catch (InterruptedException e) {
//                            sensorMonitor.setBlocked(false);
//                            e.printStackTrace();
//                        }
//                    } else {
//                        try {
//                            if (rs3.getState()) rs3.relayOff();
//                            sensorMonitor.setBlocked(false);
//                        } catch (InterruptedException e) {
//                            sensorMonitor.setBlocked(false);
//                            e.printStackTrace();
//                        }
//                    }
//                    break;
                }
                case 4: {
                }
                case 5: {
                }
                case 6: {
                }
                default: {
                    if (!sensorMonitor.isBlocked()) {
                        sensorMonitor.setBlocked(true);
                        sensorMonitor.setActiveSensor1(true);
                        sensorMonitor.setActiveSensor2(false);
                        sensorMonitor.setActiveSensor3(false);
                        sensorMonitor.setActiveSensor4(false);
                        sensorMonitor.setActiveSensor5(false);
                        sensorMonitor.setActiveSensor6(true);
                        sensorMonitor.setBlocked(false);
                        sensorMonitor.setWhoBlocked(0);
                    }
                    System.out.println("IDLE");
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    sensorMonitor.setBlocked(false);
                    break;
                }
            }
        }
        System.exit(0);
    }

    public void monitorStop() {
        isStopped = true;
    }

}
