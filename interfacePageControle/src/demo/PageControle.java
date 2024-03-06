package demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class PageControle extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblM_3;
	private JLabel lblM_4;
	private JLabel lblM_5;
	private JCheckBox chckbxNewCheckBox_4;
	private JCheckBox chckbxNewCheckBox_5;

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
		setBounds(100, 100, 800, 800);
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
		
		JLabel lblM = new JLabel("M1");
		lblM.setBounds(176, 183, 21, 27);
		contentPane.add(lblM);
		
		JLabel lblM_1 = new JLabel("M2");
		lblM_1.setBounds(277, 189, 26, 14);
		contentPane.add(lblM_1);
		
		JLabel lblM_2 = new JLabel("M3");
		lblM_2.setBounds(366, 189, 21, 14);
		contentPane.add(lblM_2);
		
		textField = new JTextField();
		textField.setBounds(138, 214, 96, 60);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(233, 214, 96, 60);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(326, 214, 96, 60);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(420, 214, 96, 60);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(515, 214, 96, 60);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(608, 214, 96, 60);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(138, 273, 96, 60);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(233, 273, 96, 60);
		contentPane.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(326, 273, 96, 60);
		contentPane.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(420, 273, 96, 60);
		contentPane.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(515, 273, 96, 60);
		contentPane.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(608, 273, 96, 60);
		contentPane.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(138, 332, 96, 60);
		contentPane.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(233, 332, 96, 60);
		contentPane.add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(326, 332, 96, 60);
		contentPane.add(textField_14);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(420, 332, 96, 60);
		contentPane.add(textField_15);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(515, 332, 96, 60);
		contentPane.add(textField_16);
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(608, 332, 96, 60);
		contentPane.add(textField_17);
		
		lblNewLabel = new JLabel("Vitesse");
		lblNewLabel.setBounds(64, 237, 49, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Angle");
		lblNewLabel_1.setBounds(64, 296, 49, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Baud Rate");
		lblNewLabel_2.setBounds(64, 355, 60, 14);
		contentPane.add(lblNewLabel_2);
		
		lblM_3 = new JLabel("M4");
		lblM_3.setBounds(456, 189, 24, 14);
		contentPane.add(lblM_3);
		
		lblM_4 = new JLabel("M5");
		lblM_4.setBounds(558, 189, 21, 14);
		contentPane.add(lblM_4);
		
		lblM_5 = new JLabel("M6");
		lblM_5.setBounds(653, 189, 21, 14);
		contentPane.add(lblM_5);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(176, 159, 21, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("");
		chckbxNewCheckBox_1.setBounds(267, 159, 21, 23);
		contentPane.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("");
		chckbxNewCheckBox_2.setBounds(366, 159, 21, 23);
		contentPane.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("");
		chckbxNewCheckBox_3.setBounds(456, 159, 21, 23);
		contentPane.add(chckbxNewCheckBox_3);
		
		chckbxNewCheckBox_4 = new JCheckBox("");
		chckbxNewCheckBox_4.setBounds(558, 159, 21, 23);
		contentPane.add(chckbxNewCheckBox_4);
		
		chckbxNewCheckBox_5 = new JCheckBox("");
		chckbxNewCheckBox_5.setBounds(653, 159, 21, 23);
		contentPane.add(chckbxNewCheckBox_5);
	}
}