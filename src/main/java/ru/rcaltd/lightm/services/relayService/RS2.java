package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

@Service
public class RS2 {

    private final static GpioController gpio = GpioFactory.getInstance();

    private final static GpioPinDigitalOutput relayPin2 = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_27, PinState.LOW);
    private final boolean DEBUG = true;

    public void relayOn() throws InterruptedException {

        relayPin2.high(); // Make relay pin HIGH
        if (DEBUG) System.out.println("relay -2- On");
        Thread.sleep(1000);
    }

    public void relayOff() throws InterruptedException {

        relayPin2.low(); // Make relay pin LOW
        if (DEBUG) System.out.println("relay -2- Off");
        Thread.sleep(100);
    }

    public boolean getState() {
        return relayPin2.getState().isHigh();
    }

}
