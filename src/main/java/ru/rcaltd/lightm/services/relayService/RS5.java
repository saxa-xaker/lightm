package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

@Service
public class RS5 {

    final static GpioController gpio = GpioFactory.getInstance();

    private static final GpioPinDigitalOutput relayPin5 = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_22, PinState.LOW);

    //    @Value("${DEBUG}")
    private final boolean DEBUG = true;
    private boolean isOn = false;

    public void relayOn() {
        // Make relay pin HIGH
        relayPin5.high();
        isOn = true;
        if (DEBUG) System.out.println("relay -5- On, isOn = " + isOn);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void relayOff() {
        // Make relay pin LOW
        relayPin5.low();
        isOn = false;
        if (DEBUG) System.out.println("relay -5- Off, isOn = " + isOn);
//        Thread.sleep(100);
    }


    public boolean getState() {
        return relayPin5.isHigh();
    }

}
