package ru.rcaltd.lightm.entities;


public class SensorMonitor {


    private boolean activeSensor1;
    private boolean activeSensor2;
    private boolean activeSensor3;
    private boolean activeSensor4;
    private boolean activeSensor5;
    private boolean activeSensor6;

    public boolean isActiveSensor1() {
        return activeSensor1;
    }

   synchronized public void setActiveSensor1(boolean activeSensor1) {
       this.activeSensor1 = activeSensor1;
   }

    public boolean isActiveSensor2() {
        return activeSensor2;
    }

    synchronized public void setActiveSensor2(boolean activeSensor2) {
        this.activeSensor2 = activeSensor2;
    }

    public boolean isActiveSensor3() {
        return activeSensor3;
    }

    synchronized public void setActiveSensor3(boolean activeSensor3) {
        this.activeSensor3 = activeSensor3;
    }

    public boolean isActiveSensor4() {
        return activeSensor4;
    }

    synchronized public void setActiveSensor4(boolean activeSensor4) {
        this.activeSensor4 = activeSensor4;
    }

    public boolean isActiveSensor5() {
        return activeSensor5;
    }

    synchronized public void setActiveSensor5(boolean activeSensor5) {
        this.activeSensor5 = activeSensor5;
    }

    public boolean isActiveSensor6() {
        return activeSensor6;
    }

    synchronized public void setActiveSensor6(boolean activeSensor6) {
        this.activeSensor6 = activeSensor6;
    }

    public SensorMonitor() {
    }
}
