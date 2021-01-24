package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

@Service
public class RS6 {

    final static GpioController gpio = GpioFactory.getInstance();

    final static GpioPinDigitalOutput relayPin6 = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_21, PinState.LOW);
    private final boolean DEBUG = true;

    public void relayOn() throws InterruptedException {

        relayPin6.high(); // Make relay pin HIGH
        if (DEBUG) System.out.println("relay -6- On");
        Thread.sleep(1000);

    }

    public void relayOff() throws InterruptedException {

        relayPin6.low(); // Make relay pin LOW
        if (DEBUG) System.out.println("relay -6- Off");
        Thread.sleep(100);

    }

    public boolean getState() {
        return relayPin6.getState().isHigh();
    }

}
