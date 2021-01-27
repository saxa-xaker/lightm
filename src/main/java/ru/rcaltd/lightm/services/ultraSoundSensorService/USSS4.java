package ru.rcaltd.lightm.services.ultraSoundSensorService;

import com.pi4j.io.gpio.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.rcaltd.lightm.entities.SensorMonitor;
import ru.rcaltd.lightm.services.relayService.RS4;

import java.text.DecimalFormat;
import java.text.Format;

@Service
public class USSS4 {
    private final static Format DF22 = new DecimalFormat("#0.00");
    private final static double SOUND_SPEED = 34_300;
    private final static double DIST_FACT = SOUND_SPEED / 2;
    @Value("${MIN_DIST}")
    private int MIN_DIST;
    @Value("${MAX_DIST}")
    private int MAX_DIST;
    private final static long BETWEEN_LOOPS = 500L;
    private final static long MAX_WAIT = 500L;
    @Value("${DEBUG}")
    private boolean DEBUG;
    final RS4 rs4;

    public USSS4(RS4 rs4) {
        this.rs4 = rs4;
    }

    public void monitorStart(SensorMonitor sensorMonitor) throws InterruptedException {

        final GpioController gpio = GpioFactory.getInstance();

        final GpioPinDigitalOutput trigPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Trig", PinState.LOW);
        final GpioPinDigitalInput echoPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_12, "Echo", PinPullResistance.PULL_DOWN);

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            System.out.println("Sensor 4 - Oops!");
            trigPin.low();
            gpio.shutdown();
            System.out.println("Sensor 4 - Exiting nicely.");
        }, "Shutdown Hook"));

        System.out.println("Sensor 4 - Waiting for the sensor1 to be ready (2s)...");
        Thread.sleep(2_000L);
        Thread mainThread = Thread.currentThread();

        boolean go = true;
        System.out.println("Sensor 4 - Looping until the distance is less than " + MIN_DIST + " cm");
        while (go) {
            boolean ok = true;
            double start = 0d, end = 0d;
            if (DEBUG) System.out.println("Sensor 4 - Triggering module.");
            TriggerThread trigger = new TriggerThread(mainThread, trigPin, echoPin);
            trigger.start();
            try {
                synchronized (mainThread) {
                    long before = System.currentTimeMillis();
                    mainThread.wait(MAX_WAIT);
                    long after = System.currentTimeMillis();
                    long diff = after - before;
                    if (DEBUG) System.out.println("Sensor 4 - MainThread done waiting (" + diff + " ms)");

                    if (diff >= MAX_WAIT) {
                        ok = false;
                        if (true || DEBUG) System.out.println("Sensor 4 ...Resetting...");
                        if (trigger.isAlive()) {
                            trigger.interrupt();
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                ok = false;
            }
            if (ok) {
                start = trigger.getStart();
                end = trigger.getEnd();
                if (DEBUG) System.out.println("Sensor 4 - Measuring...");
                if (end > 0 && start > 0) {
                    double pulseDuration = (end - start) / 1E9; // in seconds
                    double distance = pulseDuration * DIST_FACT;

                    if (distance > MIN_DIST && distance < MAX_DIST
                            && sensorMonitor.isActiveSensor4()) {
                        sensorMonitor.setActiveSensor1(false);
                        sensorMonitor.setActiveSensor2(false);
                        sensorMonitor.setActiveSensor3(false);
                        sensorMonitor.setActiveSensor4(true);
                        sensorMonitor.setActiveSensor5(false);
                        sensorMonitor.setActiveSensor6(false);
                        rs4.relayOn();
                        System.out.println(distance);
                    } else {
                        if (distance < 0) {
                            go = false;
                            System.out.println("Sensor 4 - Dist:" + distance + ", start:" + start + ", end:" + end);
                        }
                        if (sensorMonitor.isActiveSensor4() && rs4.getState()) {

                            sensorMonitor.setActiveSensor1(false);
                            sensorMonitor.setActiveSensor2(false);
                            sensorMonitor.setActiveSensor3(true);
                            sensorMonitor.setActiveSensor4(true);
                            sensorMonitor.setActiveSensor5(true);
                            sensorMonitor.setActiveSensor6(false);
                            rs4.relayOff();
                            System.out.println(distance);
                        }
                        try {

                            Thread.sleep(BETWEEN_LOOPS);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                } else {
                    System.out.println("Sensor 4 - Hiccup!");
                }
            }
        }
        System.out.println("Sensor 4 - Done.");
        trigPin.low(); // Off
        gpio.shutdown();
        System.exit(0);
    }

    private static class TriggerThread extends Thread {
        private GpioPinDigitalOutput trigPin = null;
        private GpioPinDigitalInput echoPin = null;
        private Thread caller = null;

        private double start = 0D, end = 0D;

        public TriggerThread(Thread parent, GpioPinDigitalOutput trigger, GpioPinDigitalInput echo) {
            this.trigPin = trigger;
            this.echoPin = echo;
            this.caller = parent;
        }

        public void run() {
            trigPin.high();
            // 10 microsec (10000 ns) to trigger the module  (8 ultrasound bursts at 40 kHz)
            try {
                Thread.sleep(0, 10_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            trigPin.low();

            // Wait for the signal to return
            while (echoPin.isLow()) {
                start = System.nanoTime();
            }
            // There it is
            while (echoPin.isHigh()) {
                end = System.nanoTime();
            }
            synchronized (caller) {
                caller.notify();
            }
        }

        public double getStart() {
            return start;
        }

        public double getEnd() {
            return end;
        }
    }
}