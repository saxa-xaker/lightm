package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RS2 {

    final static GpioController gpio = GpioFactory.getInstance();

    private static final GpioPinDigitalOutput relayPin2 = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_27, PinState.LOW);

    @Value("${DEBUG}")
    private boolean DEBUG;
    private boolean isOn;
    public void relayOn() {
        // Make relay pin HIGH
        relayPin2.high();
        isOn = true;
        if (DEBUG) System.out.println("relay -2- On");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void relayOff() {
        // Make relay pin LOW
        relayPin2.low();
        isOn = false;
        if (DEBUG) System.out.println("relay -2- Off");
//        Thread.sleep(100);
    }


    public boolean getState() {
        return isOn;
    }

}
