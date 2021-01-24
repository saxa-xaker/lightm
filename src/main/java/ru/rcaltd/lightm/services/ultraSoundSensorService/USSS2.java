package ru.rcaltd.lightm.services.ultraSoundSensorService;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;
import ru.rcaltd.lightm.services.relayService.RS2;

import java.text.DecimalFormat;
import java.text.Format;

@Service
public class USSS2 {

    private final static Format DF22 = new DecimalFormat("#0.00");
    private final static double SOUND_SPEED = 34_300;
    private final static double DIST_FACT = SOUND_SPEED / 2;
    private final static int MIN_DIST = 10;
    private final static int MAX_DIST = 60;
    private final static long BETWEEN_LOOPS = 500L;
    private final static long MAX_WAIT = 500L;
    private final static boolean DEBUG = true;
    final RS2 rs2;

    public USSS2(RS2 rs2) {
        this.rs2 = rs2;
    }

    public void monitorStart() throws InterruptedException {
        System.out.println("Will stop is distance is smaller than " + MIN_DIST + " cm");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        final GpioPinDigitalOutput trigPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Trig", PinState.LOW);
        final GpioPinDigitalInput echoPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, "Echo", PinPullResistance.PULL_DOWN);

        Runtime.getRuntime().

                addShutdownHook(new Thread(() ->

                {
                    System.out.println("Sensor 2 - Oops!");
                    trigPin.low();
                    gpio.shutdown();
                    System.out.println("Sensor 2 - Exiting nicely.");
                }, "Shutdown Hook"));

        System.out.println("Sensor 2 - Waiting for the sensor to be ready (2s)...");
        Thread.sleep(2_000L);
        Thread mainThread = Thread.currentThread();

        boolean go = true;
        System.out.println("Sensor 2 - Looping until the distance is less than " + MIN_DIST + " cm");
        while (go) {
            boolean ok = true;
            double start = 0d, end = 0d;
            if (DEBUG) System.out.println("Sensor 2 - Triggering module.");
            TriggerThread trigger = new TriggerThread(mainThread, trigPin, echoPin);
            trigger.start();
            try {
                synchronized (mainThread) {
                    long before = System.currentTimeMillis();
                    mainThread.wait(MAX_WAIT);
                    long after = System.currentTimeMillis();
                    long diff = after - before;
                    if (DEBUG) {
                        System.out.println("Sensor 2 - MainThread done waiting (" + diff + " ms)");
                    }
                    if (diff >= MAX_WAIT) {
                        ok = false;
                        if (true || DEBUG) System.out.println("Sensor 2 - ...Resetting...");
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
                if (DEBUG) {
                    System.out.println("Sensor 2 - Measuring...");
                }
                if (end > 0 && start > 0) {
                    double pulseDuration = (end - start) / 1E9; // in seconds
                    double distance = pulseDuration * DIST_FACT;

                    if (distance > MIN_DIST && distance < MAX_DIST) {
                        if (!rs2.getState()) {
                            rs2.relayOn();
                        }
                    } else {
                        if (distance < 0) {
                            go = false;
                            System.out.println("Sensor 2 - Dist:" + distance + ", start:" + start + ", end:" + end);
                        }
                        try {
                            Thread.sleep(BETWEEN_LOOPS);
                            if (rs2.getState()) {
                                rs2.relayOff();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("Sensor 2 - Hiccup!");
                }
            }
        }
        System.out.println("Sensor 2 - Done.");
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