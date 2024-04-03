/**
 * Project: BRAs - Interface Graphique
 * File: UART.java
 * Author: Fabrice Renard
 * Date: 2024-04-03
 * Description: UART helper class which handles the transmission
 *              of data to microcontroller
 */

package ca.polymtl.heka;

import purejavacomm.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UART {
    public static final int DEFAULT_BAUD_RATE = 115200;
    private static final int NUMBER_OF_BYTES = 4;
    private static final int UART_INIT_DELAY = 2000;

    public static List<String> getSerialPortsList() {
        List<String> portNames = new ArrayList<>();
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        while (portEnum.hasMoreElements()) {
            CommPortIdentifier portIdentifier = (CommPortIdentifier) portEnum.nextElement();
            portNames.add(portIdentifier.getName());
        }

        return portNames;
    }

    public static void convertDataToTxt(int velocity, int angle) {
        assert(velocity >= 0 && velocity <= 100 && angle >= 0 && angle <= 360);
        int data = ((velocity & 0xFF) | ((angle & 0xFF) << 8));
        try (FileWriter file = new FileWriter("data.txt")) {
            file.write(Integer.toHexString(data) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean sendDataThroughUART(String serialPortName, Integer baudRate, String dataFileName, String dataFilePath) {
        String dataFilePathWithName = dataFilePath + dataFileName;
        boolean dataSuccessfullySent = false;
        int data = 0x00000000;

        try (FileReader fileReader = new FileReader(dataFilePathWithName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data += Integer.parseInt(line, 16);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(serialPortName);
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Error: Port is currently in use");
            } else {
                SerialPort serialPort = (SerialPort) portIdentifier.open("UART", UART_INIT_DELAY);
                serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                byte[] byteData = new byte[NUMBER_OF_BYTES];
                for (int i = 0; i < NUMBER_OF_BYTES; i++) {
                    byteData[i] = (byte) (data >> (i * 8));
                }

                serialPort.getOutputStream().write(byteData);
                dataSuccessfullySent = true;

                System.out.println("Data sent: 0x" + bytesToHex(byteData));

                serialPort.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataSuccessfullySent;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
