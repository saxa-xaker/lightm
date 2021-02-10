Algo:
System have to looking all 6 sensors and turn on the relays to correspond to sensors.

1. HC-SR04 - 6 sensors 
(look for http://wikihandbk.com/ruwiki/images/f/f9/Rasp2_hc-sr04.png)
2. Lamps
3. Relays
4. Wiring
5. Raspberry pi 4B+ with Raspbian
(https://pinout.xyz/pinout/)
To install - clone project, goto lightm directory, then "mvn clean package", then "java -jar /...path../..to.../lightm-2.0-RC.jar"
Project required openJDK11 or higher, wiringPi 2.52 or higher, Pi4J library.
Code to makes sensors work got from https://github.com/OlivierLD/raspberry-coffee/blob/master/HC-SR04/src/main/java/rangesensor/HC_SR04andLeds.java and a little upgraded for me.
