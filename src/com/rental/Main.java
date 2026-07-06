package com.rental;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // This tells Java to safely start the GUI on the correct thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RentalGUI().setVisible(true);
            }
        });
    }
}