package ru.rcaltd.lightm.services;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rcaltd.lightm.services.ultraSoundSensorService.USSS;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class MonitorService {

    final ReentrantLock locker = new ReentrantLock();
    final USSS usss1;
    final USSS usss2;
    final USSS usss3;
    final USSS usss4;
    final USSS usss5;
    final USSS usss6;
    @Autowired
    PowerService powerService;

    public MonitorService() {

        this.usss1 = new USSS();
        this.usss2 = new USSS();
        this.usss3 = new USSS();
        this.usss4 = new USSS();
        this.usss5 = new USSS();
        this.usss6 = new USSS();
    }

    public void monitorsStart() {

        monitorStart(usss1, RaspiPin.GPIO_25, RaspiPin.GPIO_07, RaspiPin.GPIO_00);
        monitorStart(usss2, RaspiPin.GPIO_27, RaspiPin.GPIO_01, RaspiPin.GPIO_02);
        monitorStart(usss3, RaspiPin.GPIO_23, RaspiPin.GPIO_03, RaspiPin.GPIO_04);
//        monitorStart(usss4, RaspiPin.GPIO_26, RaspiPin.GPIO_05, RaspiPin.GPIO_12);
//        monitorStart(usss5, RaspiPin.GPIO_22, RaspiPin.GPIO_13, RaspiPin.GPIO_06);
//        monitorStart(usss6, RaspiPin.GPIO_21, RaspiPin.GPIO_14, RaspiPin.GPIO_10);

        System.out.println("INIT DONE");

    }

    private void monitorStart(USSS usss, Pin relay, Pin trig, Pin echo) {
        new Thread(() -> {
            try {
                usss.monitorStart(relay, trig, echo, locker);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + " is down. LightM - restart now!");
                powerService.lightmRestart();
            }
        }).start();
    }

    public void monitorStop() {
        System.exit(0);
    }

}
