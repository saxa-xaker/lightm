package ru.rcaltd.lightm.services.ultraSoundSensorService;

import com.pi4j.io.gpio.*;
import ru.rcaltd.lightm.services.relayService.RS;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.concurrent.locks.ReentrantLock;

public class USSS {
    private final static Format DF22 = new DecimalFormat("#0.00");
    private final static double SOUND_SPEED = 34_300;
    private final static double DIST_FACT = SOUND_SPEED / 2;
    private final static long MAX_WAIT = 50L;
    final RS rs;
    private final boolean DEBUG = false;

    public USSS(RS rs) {
        this.rs = rs;
    }

    public void monitorStart(Pin _trigPin, Pin _echoPin, ReentrantLock reentrantLock) throws InterruptedException {

        final GpioController gpio = GpioFactory.getInstance();

        final GpioPinDigitalOutput trigPin = gpio.provisionDigitalOutputPin(_trigPin, "Trig", PinState.LOW);
        final GpioPinDigitalInput echoPin = gpio.provisionDigitalInputPin(_echoPin, "Echo", PinPullResistance.PULL_DOWN);

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            trigPin.low();
            gpio.shutdown();
            System.out.println("Sensor " + _echoPin.getName() + " - Exiting nicely.");
        }, "Shutdown Hook"));

        System.out.println("Sensor " + _echoPin.getName()
                + " - Waiting for the sensor1 to be ready (2s)...");
        Thread.sleep(2_000L);
        Thread mainThread = Thread.currentThread();

        boolean go = true;
//        System.out.println("Sensor " + _echoPin.getName()
//                + " - Looping until the distance is less than " + 10 + " cm");
        while (go) {
            reentrantLock.lock();
            System.out.println("Lock " + Thread.currentThread().getName());
            boolean ok = true;
            double start, end;
            if (DEBUG) System.out.println("Sensor " + _echoPin.getName()
                    + " - Triggering module.");
            TriggerThread trigger = new TriggerThread(mainThread, trigPin, echoPin);
            trigger.start();
            try {
                synchronized (mainThread) {
                    long before = System.currentTimeMillis();
                    mainThread.wait(MAX_WAIT);
                    long after = System.currentTimeMillis();
                    long diff = after - before;

                    if (diff >= MAX_WAIT) {
                        ok = false;
                        System.out.println("Sensor " + _echoPin.getName() + " ...Resetting...");
                        if (trigger.isAlive()) {
                            trigger.interrupt();
                            trigger.join();
                        }
                    }
                }
            } catch (Exception ex) {
                ex.getCause();
                ok = false;
            }
            if (ok) {
                start = trigger.getStart();
                end = trigger.getEnd();
//                if (DEBUG) System.out.println("Sensor " + _echoPin.getName() + " - Measuring...");
                if (end > 0 && start > 0) {
                    double pulseDuration = (end - start) / 1E9; // in seconds
                    double distance = pulseDuration * DIST_FACT;

                    if (distance > 10 && distance < 40) {
                        rs.relayOn();
                        System.out.println(distance);
                    } else {
                        if (distance < 0) {
                            go = false;
                            System.out.println("Sensor " + _echoPin.getName()
                                    + " - Dist:" + distance + ", start:" + start + ", end:" + end);
                        }
                        if (rs.getState()) {
                            rs.relayOff();
                            System.out.println(distance);
                        }
                    }
                }
//                else {
//                    System.out.println("Sensor " + _echoPin.getName() + " - Hiccup!");
//                }
            }
            Thread.sleep(10);
            System.out.println("Unlock " + Thread.currentThread().getName());
            reentrantLock.unlock();
            Thread.sleep(400);
        }
        trigPin.low(); // Off
        gpio.shutdown();
        System.exit(0);
    }

    private static class TriggerThread extends Thread {
        private final GpioPinDigitalOutput trigPin;
        private final GpioPinDigitalInput echoPin;
        private final Thread caller;
        private double start = 0D, end = 0D;

        public TriggerThread(Thread parent, GpioPinDigitalOutput trigger, GpioPinDigitalInput echo) {
            this.trigPin = trigger;
            this.echoPin = echo;
            this.caller = parent;
        }

        public void run() {
            trigPin.high();
            // 10 micros (10_000 ns) to trigger the module  (8 ultrasound bursts at 40 kHz)
            try {
                Thread.sleep(0, 10_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            trigPin.low();

            // Wait for the signal to return 5 milliseconds maximum
            long echoWaitStart = System.currentTimeMillis();
            while ((System.currentTimeMillis() - echoWaitStart) < 5 && echoPin.isLow()) {
                start = System.nanoTime();
            }
            // Wait for the signal to return 5 milliseconds maximum
            long echoWaitFinish = System.currentTimeMillis();
            while ((System.currentTimeMillis() - echoWaitFinish) < 5 && echoPin.isHigh()) {
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
