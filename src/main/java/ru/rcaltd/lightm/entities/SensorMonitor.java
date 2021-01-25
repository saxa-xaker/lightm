package ru.rcaltd.lightm.entities;


public class SensorMonitor {

    private boolean sensorOn1;
    private boolean sensorOn2;
    private boolean sensorOn3;
    private boolean sensorOn4;
    private boolean sensorOn5;
    private boolean sensorOn6;
    private boolean activeSensor1;
    private boolean activeSensor2;
    private boolean activeSensor3;
    private boolean activeSensor4;
    private boolean activeSensor5;
    private boolean activeSensor6;
    private boolean isBlocked;
    private int whoBlocked;

    public int getWhoBlocked() {
        return whoBlocked;
    }

    public void setWhoBlocked(int whoBlocked) {
        this.whoBlocked = whoBlocked;
    }

    synchronized public boolean isBlocked() {
        return isBlocked;
    }

    synchronized public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isSensorOn1() {
        return sensorOn1;
    }

    public void setSensorOn1(boolean sensorOn1) {
        this.sensorOn1 = sensorOn1;
    }

    public boolean isSensorOn2() {
        return sensorOn2;
    }

    public void setSensorOn2(boolean sensorOn2) {
        this.sensorOn2 = sensorOn2;
    }

    public boolean isSensorOn3() {
        return sensorOn3;
    }

    public void setSensorOn3(boolean sensorOn3) {
        this.sensorOn3 = sensorOn3;
    }

    public boolean isSensorOn4() {
        return sensorOn4;
    }

    public void setSensorOn4(boolean sensorOn4) {
        this.sensorOn4 = sensorOn4;
    }

    public boolean isSensorOn5() {
        return sensorOn5;
    }

    public void setSensorOn5(boolean sensorOn5) {
        this.sensorOn5 = sensorOn5;
    }

    public boolean isSensorOn6() {
        return sensorOn6;
    }

    public void setSensorOn6(boolean sensorOn6) {
        this.sensorOn6 = sensorOn6;
    }

    public boolean isActiveSensor1() {
        return activeSensor1;
    }

    public void setActiveSensor1(boolean activeSensor1) {
        this.activeSensor1 = activeSensor1;
    }

    public boolean isActiveSensor2() {
        return activeSensor2;
    }

    public void setActiveSensor2(boolean activeSensor2) {
        this.activeSensor2 = activeSensor2;
    }

    public boolean isActiveSensor3() {
        return activeSensor3;
    }

    public void setActiveSensor3(boolean activeSensor3) {
        this.activeSensor3 = activeSensor3;
    }

    public boolean isActiveSensor4() {
        return activeSensor4;
    }

    public void setActiveSensor4(boolean activeSensor4) {
        this.activeSensor4 = activeSensor4;
    }

    public boolean isActiveSensor5() {
        return activeSensor5;
    }

    public void setActiveSensor5(boolean activeSensor5) {
        this.activeSensor5 = activeSensor5;
    }

    public boolean isActiveSensor6() {
        return activeSensor6;
    }

    public void setActiveSensor6(boolean activeSensor6) {
        this.activeSensor6 = activeSensor6;
    }

    public SensorMonitor() {
    }


}
