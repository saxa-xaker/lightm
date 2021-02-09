package ru.rcaltd.lightm.services.ultraSoundSensorService;

import com.pi4j.io.gpio.*;

import java.util.concurrent.locks.ReentrantLock;

public class USSS {
    private final boolean DEBUG = false;
    final GpioController gpio = GpioFactory.getInstance();

    public void monitorStart(Pin _relayPin, Pin _trigPin, Pin _echoPin, ReentrantLock _locker) throws InterruptedException {

        final GpioPinDigitalOutput trigPin = gpio.provisionDigitalOutputPin(_trigPin, "Trig", PinState.LOW);
        final GpioPinDigitalInput echoPin = gpio.provisionDigitalInputPin(_echoPin, "Echo", PinPullResistance.PULL_DOWN);
        final GpioPinDigitalOutput relayPin = gpio.provisionDigitalOutputPin(_relayPin, "Relay", PinState.LOW);

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            trigPin.low();
            gpio.shutdown();
            System.out.println("Sensor " + _echoPin.getName() + " - Exiting nicely.");
        }, "Shutdown Hook"));

        System.out.println("Sensor " + _echoPin.getName()
                + " - Waiting for the sensor to be ready (2s)...");
        Thread.sleep(2_000L);
        Thread mainThread = Thread.currentThread();

        boolean go = true;
        if (DEBUG) System.out.println("Sensor " + _echoPin.getName()
                + " - Looping until the distance is less than " + 10 + " cm");
        while (go) {
            _locker.lock();
            try {
                if (DEBUG) System.out.println("Lock " + Thread.currentThread().getName());
                boolean ok = true;
                double start, end;
                if (DEBUG) System.out.println("Sensor " + _echoPin.getName()
                        + " - Triggering module.");
                TriggerThread trigger = new TriggerThread(mainThread, trigPin, echoPin);
                trigger.start();
                try {
                    synchronized (mainThread) {
                        long before = System.currentTimeMillis();
                        mainThread.wait(20L);
                        long after = System.currentTimeMillis();

                        if ((after - before) >= 20L) {
                            ok = false;
                            if (DEBUG) System.out.println("Sensor " + _echoPin.getName() + " ...Resetting...");
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
                    if (DEBUG) System.out.println("Sensor " + _echoPin.getName() + " - Measuring...");
                    if (end > 0 && start > 0) {
                        double distance = ((end - start) / 1E9) * (34_300d / 2d);

                        if (distance > 10 && distance < 100) {
                            relayPin.high();
                            if (DEBUG) System.out.println(distance + ", relay " + relayPin.getName());
                        } else {
                            if (distance < 0) {
                                go = false;
                                if (DEBUG) System.out.println("Sensor " + _echoPin.getName()
                                        + " - Dist:" + distance + ", start:" + start + ", end:" + end);
                            }
                            if (relayPin.getState().isHigh()) {
                                relayPin.low();
                                if (DEBUG) System.out.println(distance);
                            }
                        }
                    } else {
                        if (DEBUG) System.out.println("Sensor " + _echoPin.getName() + " - Hiccup!");
                    }
                }
                Thread.sleep(40);
                if (DEBUG) System.out.println("Unlock " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                _locker.unlock();
                Thread.sleep(180);
            }
        }
        trigPin.low();
        gpio.shutdown();
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

            // Wait for the signal to return 7 milliseconds maximum
            long echoWaitStart = System.currentTimeMillis();
            while ((System.currentTimeMillis() - echoWaitStart) < 7 && echoPin.isLow()) {
                start = System.nanoTime();
            }
            // Wait for the signal length 7 milliseconds maximum
            long echoWaitFinish = System.currentTimeMillis();
            while ((System.currentTimeMillis() - echoWaitFinish) < 7 && echoPin.isHigh()) {
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
