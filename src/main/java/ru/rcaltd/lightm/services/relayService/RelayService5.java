package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

@Service
public class RelayService5 {

    final static GpioController gpio = GpioFactory.getInstance();

    private static final GpioPinDigitalOutput relayPin = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_26, PinState.LOW);

    public void relayOn() {

        relayPin.high(); // Make relay pin HIGH
    }

    public void relayOff() {

        relayPin.low(); // Make relay pin LOW
    }


    public String getState() {
        return relayPin.getState().toString();
    }

}
