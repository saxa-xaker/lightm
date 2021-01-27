package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

@Service
public class RS2 {

    final static GpioController gpio = GpioFactory.getInstance();

    private static final GpioPinDigitalOutput relayPin2 = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_27, PinState.LOW);

    //    @Value("${DEBUG}")
    private final boolean DEBUG = true;
    private boolean isOn = false;

    public void relayOn() {
        // Make relay pin HIGH
        relayPin2.high();
        isOn = true;
        if (DEBUG) System.out.println("relay -2- On, isOn = " + isOn);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void relayOff() {
        // Make relay pin LOW
        relayPin2.low();
        isOn = false;
        if (DEBUG) System.out.println("relay -2- Off, isOn = " + isOn);
//        Thread.sleep(100);
    }


    public boolean getState() {
        return relayPin2.isHigh();
    }

}
