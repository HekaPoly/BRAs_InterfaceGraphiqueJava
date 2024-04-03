/**
 * Project: BRAs - Interface Graphique
 * File: PageControle.java
 * Author: Olivia Danan
 * Date: 2024-04-03
 * Description: Control view, used to send data to
 *              microcontroller
 */

package ca.polymtl.heka;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class PageControle extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private Color GREY = new Color(150, 150, 150);

    private static final int NUM_ROWS = 3;
    private static final int NUM_COLS = 5;

    private boolean validSpeed(String text) {
        if (text.isBlank()) {
            return true;
        }
        int value = Integer.parseInt(text);
        return value >= 0 && value <= 360;
    }

    private boolean validAngle(String text) {
        if (text.isBlank()) {
            return true;
        }
        int value = Integer.parseInt(text);
        return value >= 0 && value <= 360;
    }

    private boolean validBaudRate(String text) {
        if (text.isBlank()) {
            return true;
        }
        int value = Integer.parseInt(text);
        Integer integerValue = Integer.valueOf(value);
        return (integerValue instanceof Integer);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    System.setProperty("sun.java2d.uiScale", "1.0");
                    PageControle frame = new PageControle();
                    frame.setVisible(true);
                    frame.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public PageControle() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel labelCopyright = new JLabel("© 2024 Héka. Tous droits réservés.");
        labelCopyright.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labelCopyright.setBounds(320, 680, 237, 17);
        contentPane.add(labelCopyright);

        JLabel labelVersion = new JLabel("v 2.01");
        labelVersion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labelVersion.setBounds(391, 650, 49, 14);
        contentPane.add(labelVersion);

        String[] rowLabels = {"Vitesse", "Angle", "Baud Rate"};
        for (int row = 0; row < NUM_ROWS; row++) {
            JLabel label = new JLabel(rowLabels[row]);
            label.setBounds(70, 100 + row * 50, 80, 30);
            contentPane.add(label);
        }

        // checkboxes with its associated text fields
        for (int col = 0; col < NUM_COLS; col++) {
            JCheckBox checkBox = new JCheckBox("M" + (col + 1));
            checkBox.setBounds(180 + col * 100, 50, 80, 25);
            contentPane.add(checkBox);

            for (int row = 0; row < NUM_ROWS; row++) {
                JTextField textField = new JTextField();
                textField.setBounds(150 + col * 100, 100 + row * 50, 100, 50);
                textField.setBackground(GREY);
                textField.setEditable(false);

                contentPane.add(textField);

                // actionListener for each checkbox
                checkBox.addActionListener(new CheckBoxListener(textField));
                // focusListener for each row textFiled
                textField.addFocusListener(new TextFieldFocusListener(textField, row));
            }
        }

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(350, 400, 100, 30);
        contentPane.add(submitButton);

        // Add action listener to submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (canSubmit()) {
                    List<Integer> data = new ArrayList<>();

                    for (Component component : contentPane.getComponents()) {
                        if (component instanceof JTextField) {
                            JTextField textField = (JTextField) component;
                            if (textField.getBounds().x == 150) {
                                try {
                                    data.add(Integer.parseInt(textField.getText()));
                                } catch (NumberFormatException err) {
                                    err.printStackTrace();
                                }

                            }
                        }
                    }

                    UART.convertDataToTxt(data.get(0), data.get(1));
                    List<String> serialPorts = UART.getSerialPortsList();

                    UART.sendDataThroughUART(
                            serialPorts.get(0),
                            data.get(2),
                            "data.txt",
                            "");
                    clearFields();
                }
            }
        });
    }

    private boolean canSubmit() {
        for (int col = 0; col < NUM_COLS; col++) {
            JCheckBox checkBox = (JCheckBox) contentPane.getComponentAt(180 + col * 100, 50);
            if (checkBox.isSelected()) {
                for (int row = 0; row < NUM_ROWS; row++) {
                    JTextField textField = (JTextField) contentPane.getComponentAt(150 + col * 100, 100 + row * 50);
                    if (textField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all rows in checked columns.", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    if(row == 0 && !(validSpeed(textField.getText()))) {
                        JOptionPane.showMessageDialog(null, "Invalid speed. Value must be between 0-100.", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    if(row == 1 && !(validAngle(textField.getText()))) {
                        JOptionPane.showMessageDialog(null, "Invalid Angle. Value must be between 0-360.", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    if(row == 2 && !(validBaudRate(textField.getText()))) {
                        JOptionPane.showMessageDialog(null, "Invalid Baud Rate", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void clearFields() {
        for (Component component : contentPane.getComponents()) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textField.setText("");
                textField.setBackground(GREY);
            }
            else if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                checkBox.setSelected(false);
            }
        }
    }

    private class CheckBoxListener implements ActionListener {
        private JTextField textFieldToUpdate;

        public CheckBoxListener(JTextField textField) {
            this.textFieldToUpdate = textField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            if (checkBox.isSelected()) {
                textFieldToUpdate.setEditable(true);
                textFieldToUpdate.setBackground(Color.WHITE);
            } else {
                textFieldToUpdate.setEditable(false);
                textFieldToUpdate.setBackground(GREY);
            }
        }
    }

    private class TextFieldFocusListener extends FocusAdapter {
        private JTextField textField;
        private int rowIndex;

        public TextFieldFocusListener(JTextField textField, int rowIndex) {
            this.textField = textField;
            this.rowIndex = rowIndex;
        }

        @Override
        public void focusLost(FocusEvent e) {
            String text = textField.getText();
            if (!isValid(text)) {
                textField.requestFocusInWindow();
            }
        }

        private boolean isValid(String text) {
            // Validate Vitesse
            if (rowIndex == 0) {
                try {
                    validSpeed(text);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid speed. Value must be between 0-100.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            // Validate Angle
            if(rowIndex == 1) {
                try {
                    validAngle(text);
                }catch(NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid Angle. Value must be between 0-360.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            // Validate Baud Rate
            if(rowIndex == 2) {
                try {
                    validBaudRate(text);
                }catch(NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid Baud Rate.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            return true;
        }
    }
}
