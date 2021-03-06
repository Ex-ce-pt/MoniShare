package com.Ex_ce_pt.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ConnectionPage extends Page {

    final Dimension PRIMARY_DIMENSION = new Dimension(1920, 1080);

    private BufferedImage currentFrame;

    public ConnectionPage() {
        setBackground(Color.white);
        setLayout(null);

        resetImage(PRIMARY_DIMENSION);
    }

    private void resetImage(Dimension dimension) {
        currentFrame = new BufferedImage(dimension.width / 4, dimension.height / 4, BufferedImage.TYPE_INT_ARGB);
        final Graphics g = currentFrame.getGraphics();
        g.setColor(PageBar.Tab.TAB_COLOR);
        g.fillRect(0, 0, currentFrame.getWidth(), currentFrame.getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(currentFrame, 100, 100, currentFrame.getWidth(), currentFrame.getHeight(), null);
    }

    @Override
    public void onKeyEvent(KeyEvent e, KeyEventType type) {

    }

    @Override
    public void onMouseEvent(MouseEvent e, MouseEventType type) {

    }

    @Override
    public void onMouseMoveEvent(MouseEvent e, MouseMoveEventType type) {

    }

}
