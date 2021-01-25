package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RS3 {

    final static GpioController gpio = GpioFactory.getInstance();

    final static GpioPinDigitalOutput relayPin3 = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_23, PinState.LOW);
    @Value("${DEBUG}")
    private boolean DEBUG;

    public void relayOn() throws InterruptedException {

        relayPin3.high(); // Make relay pin HIGH
        if (DEBUG) System.out.println("relay -3- On");
//        Thread.sleep(1000);

    }

    public void relayOff() throws InterruptedException {

        relayPin3.low(); // Make relay pin LOW
        if (DEBUG) System.out.println("relay -3- Off");
//        Thread.sleep(100);

    }

    public boolean getState() {
        return relayPin3.getState().isHigh();
    }

}
