package org.example;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SerialCommunicationHelper {
    public static final int DEFAULT_BAUD_RATE = 115200;
    private static final int VELOCITY_SHIFT = 0;
    private static final int ANGLE_SHIFT = 16;
    private static final int NUMBER_OF_BYTES = 4;
    private static final int UART_INIT_DELAY = 2000;

    public static String[] getSerialPortsList() {
        return SerialPortList.getPortNames();
    }

    public static void convertDataToTxt(int velocity, int angle) {
        assert(velocity >= 0 && velocity <= 100 && angle >= 0 && angle <= 360);
        velocity <<= VELOCITY_SHIFT;
        angle <<= ANGLE_SHIFT;
        int[] lines = {velocity, angle};

        try (FileWriter file = new FileWriter("data.txt")) {
            for (int line : lines) {
                file.write(Integer.toHexString(line) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean sendDataThroughUART(String serialPortName, int baudRate, String dataFileName, String dataFilePath) {
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

        SerialPort serialPort = new SerialPort(serialPortName);
        try {
            serialPort.openPort();
            serialPort.setParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            try {
                Thread.sleep(UART_INIT_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            byte[] byteData = new byte[NUMBER_OF_BYTES];
            for (int i = 0; i < NUMBER_OF_BYTES; i++) {
                byteData[i] = (byte) (data >> (i * 8));
            }

            serialPort.writeBytes(byteData);
            dataSuccessfullySent = true;

            System.out.println("Data sent: 0x" + bytesToHex(byteData));
        } catch (SerialPortException e) {
            e.printStackTrace();
        } finally {
            if (serialPort.isOpened()) {
                try {
                    serialPort.closePort();
                } catch (SerialPortException e) {
                    e.printStackTrace();
                }
            }
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

