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
    private static boolean isStop;
    private double distance = 0;

    @Async
    public void monitorStart() {
        isStop = false;
        while (!isStop) {
            try {
                Thread.sleep(250);
                sensorTriggerPin.high(); // Make trigger pin HIGH
                Thread.sleep((long) 0.01);// Delay for 10 microseconds
                sensorTriggerPin.low(); //Make trigger pin LOW

                //Wait until the ECHO pin gets HIGH
                while (sensorEchoPin.isLow()) {
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
        isStop = true;
    }

    public double getDistance() {
        return distance;
    }

    public String getState() {
        String state;
        if (distance > 40 && distance < 150) {
            state = "HIGH";
        } else {
            state = "LOW";
        }
        return state;
    }

    public void setDistanceZero() {
        distance = 0;
    }
}
