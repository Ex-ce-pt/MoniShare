package com.Ex_ce_pt;

import javax.swing.JLabel;
import java.awt.Font;

public class ErrorPanel extends javax.swing.JPanel {

    private JLabel label;

    public ErrorPanel() {
        setLayout(new java.awt.GridBagLayout());

        label = new JLabel();
        label.setText("Java Robot class is not supported on this device.");
        label.setFont(new Font("Consolas", Font.PLAIN, 24));

        add(label);
    }

}
