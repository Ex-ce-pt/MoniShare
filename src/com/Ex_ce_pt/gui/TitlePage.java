package com.Ex_ce_pt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TitlePage extends Page {

    private final MainLabelPanel mainLabelPanel;

    public TitlePage() {
        setBackground(Color.WHITE);
        setLayout(null);

        mainLabelPanel = new MainLabelPanel();

        mainLabelPanel.setLocation(300, 50);

        add(mainLabelPanel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillRect(50, 50, 150, 200);
        g.setColor(Color.CYAN);
        g.fillRect(100, 100, 150, 200);
    }

    @Override
    public void onKeyEvent(KeyEvent e, KeyEventType type) {
        System.out.println(e.getKeyChar());
    }

    @Override
    public void onMouseEvent(MouseEvent e, MouseEventType type) {

    }

    @Override
    public void onMouseMoveEvent(MouseEvent e, MouseMoveEventType type) {

    }

    private static class MainLabelPanel extends JPanel {

        private static final String SUCCESS_MESSAGE = "Copied!";
        private static final String FAILURE_MESSAGE = "Failed to copy";
        private static final long ANIMATION_DELAY = 2_000;

        private final JTextField ipField;
        private final JLabel label;
        private final JButton copyButton;
        private final JLabel stateLabel;

        private long animationStopTime;
        private Thread animationThread;
        private boolean succeeded;

        public MainLabelPanel() {
            setBackground(new Color(240, 240, 240));
            setSize(new Dimension(350, 200));
            setLayout(null);
            setBorder(BorderFactory.createEtchedBorder());

            ipField = new JTextField();
            ipField.setText(tryGetIP());
            ipField.setEditable(false);
            ipField.setFont(new Font("Times New Rowman", Font.BOLD, 20));
            ipField.setHorizontalAlignment(JTextField.CENTER);
            ipField.setBounds(10, 70, 200, 50);

            label = new JLabel("This is your IP address:");
            label.setFont(new Font("Times New Rowman", Font.PLAIN, 18));
            label.setBounds(10, -10, 200, 100);

            copyButton = new JButton("Copy to clipboard");
            copyButton.setBounds(25, 150,140, 40);
            copyButton.setBackground(new Color(150, 150, 255));
            copyButton.setForeground(Color.white);
            copyButton.setFont(new Font("Times New Rowman", Font.BOLD, 12));

            stateLabel = new JLabel();
            stateLabel.setFont(new Font("Arial", Font.BOLD, 12));
            stateLabel.setBounds(200, 150, 100, 50);

            copyButton.addActionListener(e -> {
                animationThread = new Thread(this::animate);
                animationThread.start();
            });

            add(ipField);
            add(label);
            add(copyButton);
            add(stateLabel);
        }

        private String tryGetIP() {
            try {
                byte[] nums = java.net.InetAddress.getLocalHost().getAddress();
                StringBuilder sb = new StringBuilder(), original = new StringBuilder();
                for (int i = 0; i < nums.length; i++) {
                    original.append((int) nums[i]);
                    sb.append(((int) nums[i]) - (int) Byte.MIN_VALUE);
                    if (i != nums.length - 1) {
                        sb.append('.');
                        original.append('.');
                    }
                }

                // [-64, -88, 1, 98]
                // [63, 39, 128, 225]
                System.out.println(original);
                System.out.println(java.util.Arrays.toString(java.net.InetAddress.getLocalHost().getAddress()));
                System.out.println(sb);

                return sb.toString();

            } catch (java.net.UnknownHostException e) {
                e.printStackTrace();
            }
            return "";
        }

        private void animate() {
            try {
                var selection = new java.awt.datatransfer.StringSelection(ipField.getText());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
                stateLabel.setText(SUCCESS_MESSAGE);
                succeeded = true;
            } catch(Exception ex) {
                stateLabel.setText(FAILURE_MESSAGE);
                succeeded = false;
                System.out.println("Exception while copying the IP address:");
                ex.printStackTrace(System.out);
            } finally {
                animationStopTime = System.currentTimeMillis() + ANIMATION_DELAY;
            }

            long now;
            while(true) {
                now = System.currentTimeMillis();
                if (animationStopTime - now <= 0) {
                    animationStopTime = -1;
                    break;
                }
                stateLabel.setForeground(new Color((succeeded) ? 0f : 1f,
                        (succeeded) ? 1f : 0f,
                        0f,
                        Math.max(0f, Math.min(1f, (float) (animationStopTime - now) / ANIMATION_DELAY))));
            }
            stateLabel.setForeground(new Color(0, 0, 0, 0));
        }

    }

}