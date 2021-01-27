package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

@Service
public class RS4 {

    final static GpioController gpio = GpioFactory.getInstance();

    private static final GpioPinDigitalOutput relayPin4 = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_26, PinState.LOW);

    //    @Value("${DEBUG}")
    private final boolean DEBUG = true;
    private boolean isOn = false;

    public void relayOn() {
        // Make relay pin HIGH
        relayPin4.high();
        isOn = true;
        if (DEBUG) System.out.println("relay -4- On, isOn = " + isOn);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void relayOff() {
        // Make relay pin LOW
        relayPin4.low();
        isOn = false;
        if (DEBUG) System.out.println("relay -4- Off, isOn = " + isOn);
//        Thread.sleep(100);
    }


    public boolean getState() {
        return relayPin4.isHigh();
    }

}
