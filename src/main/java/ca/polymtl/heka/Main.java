package ca.polymtl.heka;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VueInitiale view = new VueInitiale();
                view.setVisible(true);
            }
        });
    }
}