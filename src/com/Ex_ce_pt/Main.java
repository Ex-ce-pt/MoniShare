package com.Ex_ce_pt;

import javax.swing.JFrame;

public class Main {

    public static JFrame frame;
    public static MainPanel panel;

    public static void main(String[] args) {
        frame = new JFrame();

        frame.setSize(800, 600);
        frame.setTitle("MoniShare");
        frame.setIconImage(new javax.swing.ImageIcon().getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setFocusable(true);

        try {
            panel = new MainPanel();
            frame.setContentPane(panel);
        } catch(java.awt.AWTException e) {
            panel = null;
            frame.setContentPane(new ErrorPanel());
        }

        frame.setVisible(true);
        frame.requestFocus();
        frame.repaint();

        System.gc();
    }

}
