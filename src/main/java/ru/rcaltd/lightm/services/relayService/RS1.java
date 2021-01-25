package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RS1 {

    final static GpioController gpio = GpioFactory.getInstance();

    private static final GpioPinDigitalOutput relayPin1 = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_25, PinState.LOW);

    @Value("${DEBUG}")
    private boolean DEBUG;

    public void relayOn() {
        // Make relay pin HIGH
        relayPin1.high();
        if (DEBUG) System.out.println("relay -1- On");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void relayOff() {
        // Make relay pin LOW
        relayPin1.low();
        if (DEBUG) System.out.println("relay -1- Off");
//        Thread.sleep(100);
    }


    public boolean getState() {
        return relayPin1.getState().isHigh();
    }

}
