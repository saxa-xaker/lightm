package ru.rcaltd.lightm.entities;

import java.util.Objects;

public class SensorMonitor {

    private boolean sensor1;
    private boolean sensor2;
    private boolean sensor3;
    private boolean sensor4;
    private boolean sensor5;
    private boolean sensor6;

    public SensorMonitor() {
    }

    public boolean isSensor1() {
        return sensor1;
    }

    public void setSensor1(boolean sensor1) {
        this.sensor1 = sensor1;
    }

    public boolean isSensor2() {
        return sensor2;
    }

    public void setSensor2(boolean sensor2) {
        this.sensor2 = sensor2;
    }

    public boolean isSensor3() {
        return sensor3;
    }

    public void setSensor3(boolean sensor3) {
        this.sensor3 = sensor3;
    }

    public boolean isSensor4() {
        return sensor4;
    }

    public void setSensor4(boolean sensor4) {
        this.sensor4 = sensor4;
    }

    public boolean isSensor5() {
        return sensor5;
    }

    public void setSensor5(boolean sensor5) {
        this.sensor5 = sensor5;
    }

    public boolean isSensor6() {
        return sensor6;
    }

    public void setSensor6(boolean sensor6) {
        this.sensor6 = sensor6;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorMonitor)) return false;
        SensorMonitor that = (SensorMonitor) o;
        return isSensor1() == that.isSensor1() &&
                isSensor2() == that.isSensor2() &&
                isSensor3() == that.isSensor3() &&
                isSensor4() == that.isSensor4() &&
                isSensor5() == that.isSensor5() &&
                isSensor6() == that.isSensor6();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSensor1(), isSensor2(), isSensor3(), isSensor4(), isSensor5(), isSensor6());
    }
}
