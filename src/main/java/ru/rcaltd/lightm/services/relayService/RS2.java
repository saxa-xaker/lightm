package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RS2 {

    final static GpioController gpio = GpioFactory.getInstance();

    private static final GpioPinDigitalOutput relayPin = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_27, PinState.LOW);

    @Async
    public void relayOn() throws InterruptedException {

        relayPin.high(); // Make relay pin HIGH
        System.out.println("relay -2- On");
        Thread.sleep(1000);
    }

    @Async
    public void relayOff() throws InterruptedException {

        relayPin.low(); // Make relay pin LOW
        System.out.println("relay -2- Off");
        Thread.sleep(1000);
    }


    public boolean getState() {
        return relayPin.getState().isHigh();
    }

}
