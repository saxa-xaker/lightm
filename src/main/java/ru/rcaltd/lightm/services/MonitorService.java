package ru.rcaltd.lightm.services;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rcaltd.lightm.services.relayService.RS;
import ru.rcaltd.lightm.services.ultraSoundSensorService.USSS;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class MonitorService {

    final ReentrantLock reentrantLock = new ReentrantLock();
    final USSS usss1;
    final USSS usss2;
    final USSS usss3;
    final USSS usss4;
    final USSS usss5;
    final USSS usss6;
    final RS rs1;
    final RS rs2;
    final RS rs3;
    final RS rs4;
    final RS rs5;
    final RS rs6;
    @Autowired
    PowerService powerService;

    public MonitorService() {

        this.rs1 = new RS(RaspiPin.GPIO_25, PinState.LOW);
        this.rs2 = new RS(RaspiPin.GPIO_27, PinState.LOW);
        this.rs3 = new RS(RaspiPin.GPIO_23, PinState.LOW);
        this.rs4 = new RS(RaspiPin.GPIO_26, PinState.LOW);
        this.rs5 = new RS(RaspiPin.GPIO_22, PinState.LOW);
        this.rs6 = new RS(RaspiPin.GPIO_21, PinState.LOW);

        this.usss1 = new USSS(rs1);
        this.usss2 = new USSS(rs2);
        this.usss3 = new USSS(rs3);
        this.usss4 = new USSS(rs4);
        this.usss5 = new USSS(rs5);
        this.usss6 = new USSS(rs6);

    }

    public void monitorsStart() {

        monitorStart(usss1, RaspiPin.GPIO_07, RaspiPin.GPIO_00);
        monitorStart(usss2, RaspiPin.GPIO_01, RaspiPin.GPIO_02);
        monitorStart(usss3, RaspiPin.GPIO_03, RaspiPin.GPIO_04);
        monitorStart(usss4, RaspiPin.GPIO_05, RaspiPin.GPIO_12);
        monitorStart(usss5, RaspiPin.GPIO_13, RaspiPin.GPIO_06);
        monitorStart(usss6, RaspiPin.GPIO_14, RaspiPin.GPIO_10);

        System.out.println("INIT DONE");

    }

    private void monitorStart(USSS usss, Pin trig, Pin echo) {
        new Thread(() -> {
            try {
                usss.monitorStart(trig, echo, reentrantLock);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + " is down. LightM - will restart!");
                powerService.lightmRestart();
            }
        }).start();
    }

    public void monitorStop() {
        System.exit(0);
    }

}
