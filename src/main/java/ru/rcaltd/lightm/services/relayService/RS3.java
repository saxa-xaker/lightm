package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

@Service
public class RS3 {

    final static GpioController gpio = GpioFactory.getInstance();

    private static final GpioPinDigitalOutput relayPin = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_23, PinState.LOW);

    public void relayOn() {

        relayPin.high(); // Make relay pin HIGH
        System.out.println("relay -3- On");
    }

    public void relayOff() {

        relayPin.low(); // Make relay pin LOW
        System.out.println("relay -3- Off");
    }


    public boolean getState() {
        return relayPin.getState().isHigh();
    }

}
