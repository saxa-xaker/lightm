package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

@Service
public class RS4 {

    final static GpioController gpio = GpioFactory.getInstance();

    final static GpioPinDigitalOutput relayPin4 = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_26, PinState.LOW);
    private final boolean DEBUG = true;

    public void relayOn() throws InterruptedException {

        relayPin4.high(); // Make relay pin HIGH
        if (DEBUG) System.out.println("relay -4- On");
        Thread.sleep(1000);

    }

    public void relayOff() throws InterruptedException {

        relayPin4.low(); // Make relay pin LOW
        if (DEBUG) System.out.println("relay -4- Off");
        Thread.sleep(100);

    }

    public boolean getState() {
        return relayPin4.getState().isHigh();
    }

}
