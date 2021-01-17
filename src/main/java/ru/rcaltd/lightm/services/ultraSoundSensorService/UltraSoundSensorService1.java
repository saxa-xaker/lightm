package ru.rcaltd.lightm.services.ultraSoundSensorService;

import com.pi4j.io.gpio.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UltraSoundSensorService1 {

    final static GpioController gpio = GpioFactory.getInstance();

    private static final GpioPinDigitalOutput sensorTriggerPin = gpio
            .provisionDigitalOutputPin(RaspiPin.GPIO_07); // Trigger pin as OUTPUT
    private static final GpioPinDigitalInput sensorEchoPin = gpio
            .provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_DOWN); // Echo pin as INPUT
    private boolean isStopped = false;
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

                //Wait until the ECHO pin gets HIGH
                while (!isStopped && sensorEchoPin.isLow()) {
//                    System.out.println("Monitor -1- waiting LOW");
                }
                long startTime = System.nanoTime(); // Store the surrent time to calculate ECHO pin HIGH time.
                while (!isStopped && sensorEchoPin.isHigh()) { //Wait until the ECHO pin gets LOW
//                    System.out.println("Monitor -1- waiting HIGH");
                }
                long endTime = System.nanoTime(); // Store the echo pin HIGH end time to calculate ECHO pin HIGH time.
                distance = ((((endTime - startTime) / 1e3) / 2) / 29.1);
                Thread.sleep(300);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void monitorStop() {
        isStopped = true;
        gpio.shutdown();
        distance = 0;
    }

    public double getDistance() {
        return distance;
    }

    public String getState() {
        if (distance > 10 && distance < 50) {
            return "HIGH";
        }
        return "LOW";
    }

    public void setDistanceZero() {
        distance = 0;
    }
}
