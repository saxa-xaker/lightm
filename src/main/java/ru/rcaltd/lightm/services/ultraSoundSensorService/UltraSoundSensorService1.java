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
    private static boolean isStoped;
    private double distance = 0;

    @Async
    public void monitorStart() {
        isStoped = false;
        while (!isStoped) {
            try {
                Thread.sleep(250);
                sensorTriggerPin.high(); // Make trigger pin HIGH
                Thread.sleep((long) 0.01);// Delay for 10 microseconds
                sensorTriggerPin.low(); //Make trigger pin LOW

                while (sensorEchoPin.isLow()) { //Wait until the ECHO pin gets HIGH
                }
                long startTime = System.nanoTime(); // Store the surrent time to calculate ECHO pin HIGH time.
                while (sensorEchoPin.isHigh()) { //Wait until the ECHO pin gets LOW
                }
                long endTime = System.nanoTime(); // Store the echo pin HIGH end time to calculate ECHO pin HIGH time.
                distance = ((((endTime - startTime) / 1e3) / 2) / 29.1);
//                System.out.println("Distance :" + distance + " cm"); //Printing out the distance in cm
//                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void monitorStop() {
        isStoped = true;
    }

    public double getDistance() {
        return distance;
    }

    public String getState() {
        return sensorEchoPin.getState().toString();
    }

    public void setDistanceZero() {
        distance = 0;
    }
}
