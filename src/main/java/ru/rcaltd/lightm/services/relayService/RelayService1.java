package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

@Service
public class RelayService1 {

    final static GpioController gpio = GpioFactory.getInstance();

    private static final GpioPinDigitalOutput relayPin = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_03, PinState.LOW);

    public void relayOn() {

        relayPin.high(); // Make relay pin HIGH
        System.out.println("relay -1- On");
    }

    public void relayOff() {

        relayPin.low(); // Make relay pin LOW
        System.out.println("relay -1- Off");
    }


    public String getState() {
        return relayPin.getState().toString();
    }

}
