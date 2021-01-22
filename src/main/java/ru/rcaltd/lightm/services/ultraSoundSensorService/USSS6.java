package ru.rcaltd.lightm.services.ultraSoundSensorService;

import com.pi4j.io.gpio.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class USSS6 {

    final static GpioController gpio = GpioFactory.getInstance();

    private static final GpioPinDigitalOutput sensorTriggerPin = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_14); // Trigger pin as OUTPUT
    private static final GpioPinDigitalInput sensorEchoPin = gpio
            .provisionDigitalInputPin(RaspiPin.GPIO_10, PinPullResistance.PULL_DOWN); // Echo pin as INPUT
    private static boolean isStopped;
    private double distance = 0;

    @Async
    public void monitorStart() {
        isStopped = false;
        while (!isStopped) {
            try {
                Thread.sleep(250);
                sensorTriggerPin.high(); // Make trigger pin HIGH
                Thread.sleep((long) 0.01);// Delay for 10 microseconds
                sensorTriggerPin.low(); //Make trigger pin LOW

                while (sensorEchoPin.isLow()) { //Wait until the ECHO pin gets HIGH
                }
                long startTime = System.nanoTime(); // Store the current time to calculate ECHO pin HIGH time.
                while (sensorEchoPin.isHigh()) { //Wait until the ECHO pin gets LOW
                }
                long endTime = System.nanoTime(); // Store the echo pin HIGH end time to calculate ECHO pin HIGH time.
                distance = ((((endTime - startTime) / 1e3) / 2) / 29.1);
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
                gpio.shutdown();
            }
        }
    }

    public void monitorStop() {
        isStopped = true;
        gpio.shutdown();
        distance = 0;
    }

    public String getState() {
        if (distance > 20 && distance < 50) {
            return "HIGH";
        }
        return "LOW";
    }
}
