package org.example;

public class Main {
    public static void main(String[] args) {
        SerialCommunicationHelper.convertDataToTxt(100, 180);
        SerialCommunicationHelper.sendDataThroughUART("/dev/ttyACM0", 9600, "data.txt", "");
    }
}