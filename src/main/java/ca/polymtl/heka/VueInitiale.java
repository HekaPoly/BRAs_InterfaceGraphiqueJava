/**
 * Project: BRAs - Interface Graphique
 * File: VueInitiale.java
 * Author: Gabriel Mejia
 * Date: 2024-04-03
 * Description: Initial view at start of program
 */

package ca.polymtl.heka;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class VueInitiale extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    System.setProperty("sun.java2d.uiScale", "1.0");
                    VueInitiale frame = new VueInitiale();
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
    public VueInitiale() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton boutonControle = new JButton("Contrôle des moteurs");
        boutonControle.setFont(new Font("Tahoma", Font.PLAIN, 18));
        boutonControle.setBounds(300, 300, 230, 92);
        boutonControle.setFocusable(false);

        boutonControle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PageControle pageControle = new PageControle();
                pageControle.setVisible(true);
            }
        });

        contentPane.add(boutonControle);

        JLabel labelCopyright = new JLabel("© 2024 Héka. Tous droits réservés.");
        labelCopyright.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labelCopyright.setBounds(320, 680, 237, 17);
        contentPane.add(labelCopyright);

        JLabel labelImage = new JLabel("");
        ImageIcon img = new ImageIcon(this.getClass().getResource("/img/logo.png"));

        labelImage.setIcon(img);
        labelImage.setBounds(138, 31, 515, 208);
        contentPane.add(labelImage);

        JLabel labelVersion = new JLabel("v 2.01");
        labelVersion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labelVersion.setBounds(391, 650, 49, 14);
        contentPane.add(labelVersion);

        JButton boutonEtat = new JButton("État des moteurs");
        boutonEtat.setFont(new Font("Tahoma", Font.PLAIN, 18));
        boutonEtat.setFocusable(false);
        boutonEtat.setBounds(300, 450, 230, 92);
        contentPane.add(boutonEtat);
    }
}
