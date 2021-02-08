package ru.rcaltd.lightm.services.relayService;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public class RS {

    private final GpioPinDigitalOutput relayPin;
    private final boolean DEBUG = true;
    private boolean isOn = false;

    public RS(Pin pin, PinState pinState) {
        relayPin = GpioFactory.getInstance().provisionDigitalOutputPin(pin, pinState);
    }

    public void relayOn() {
        relayPin.high();
        isOn = true;
        if (DEBUG) System.out.println("Thread.name = "
                + Thread.currentThread().getName() + ", relay -"
                + relayPin.getName() + "- On, isOn = " + isOn);
    }

    public void relayOff() {
        relayPin.low();
        isOn = false;
        if (DEBUG) System.out.println("Thread.name = "
                + Thread.currentThread().getName() + ", relay -"
                + relayPin.getName() + "- Off, isOn = " + isOn);
    }

    public boolean getState() {
        return relayPin.isHigh();
    }

}
