package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RS5 {

    final static GpioController gpio = GpioFactory.getInstance();

    final static GpioPinDigitalOutput relayPin5 = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_22, PinState.LOW);
    @Value("${DEBUG}")
    private boolean DEBUG;

    public void relayOn() throws InterruptedException {

        relayPin5.high(); // Make relay pin HIGH
        if (DEBUG) System.out.println("relay -5- On");
//        Thread.sleep(1000);

    }

    public void relayOff() throws InterruptedException {

        relayPin5.low(); // Make relay pin LOW
        if (DEBUG) System.out.println("relay -5- Off");
//        Thread.sleep(100);

    }

    public boolean getState() {
        return relayPin5.getState().isHigh();
    }

}
