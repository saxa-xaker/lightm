package ru.rcaltd.lightm.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PowerService {

    public void lightmRestart() throws IOException {
        Runtime.getRuntime().exec("systemctl restart lightm");
    }

    public void lightmShutdown() throws IOException {
        Runtime.getRuntime().exec("systemctl stop lightm");
    }

    public void systemRestart() throws IOException {
        Runtime.getRuntime().exec("reboot");
    }

    public void systemShutdown() throws IOException {
        Runtime.getRuntime().exec("shutdown -P now");
    }

//    public void hardReset() throws IOException {
//        Runtime.getRuntime().exec("sh /opt/lightm/scripts/init/hardReset.sh");
//    }
//
//    public void apAutoConnect() throws IOException {
//        Runtime.getRuntime().exec("sh /opt/lightm/scripts/init/apAutoConnect.sh");
//    }
}
