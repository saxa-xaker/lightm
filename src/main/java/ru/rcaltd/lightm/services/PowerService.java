package ru.rcaltd.lightm.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PowerService {

    public void lightmRestart() {
        try {
            Runtime.getRuntime().exec("systemctl restart lightm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lightmShutdown() {
        try {
            Runtime.getRuntime().exec("systemctl stop lightm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void systemRestart() throws IOException {
        Runtime.getRuntime().exec("reboot");
    }

    public void systemShutdown() throws IOException {
        Runtime.getRuntime().exec("shutdown -P now");
    }

    public void apAutoConnect() throws IOException {
        Runtime.getRuntime().exec("sh /opt/lightm/scripts/init/apAutoConnect.sh");
    }
}
