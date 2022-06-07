package com.Ex_ce_pt.gui;

import java.awt.event.*;

public class PageListener implements KeyListener, MouseListener, MouseMotionListener {

    private Page focusedPage;

    public synchronized void attach(java.awt.Component comp) {
        comp.addKeyListener(this);
        comp.addMouseListener(this);
        comp.addMouseMotionListener(this);
    }

    public synchronized void detach(java.awt.Component comp) {
        comp.removeKeyListener(this);
        comp.removeMouseListener(this);
        comp.removeMouseMotionListener(this);
    }

    public synchronized void setFocusedPage(Page page) {
        focusedPage = page;
    }

    public synchronized Page getFocusedPage() {
        return focusedPage;
    }

    private void dispatchKeyEvent(KeyEvent e, Page.KeyEventType type) {
        if (focusedPage != null)
            focusedPage.onKeyEvent(e, type);
    }

    private void dispatchMouseEvent(MouseEvent e, Page.MouseEventType type) {
        if (focusedPage != null)
            focusedPage.onMouseEvent(e, type);
    }

    private void dispatchMouseMoveEvent(MouseEvent e, Page.MouseMoveEventType type) {
        if (focusedPage != null)
            focusedPage.onMouseMoveEvent(e, type);
    }

    @Override
    public synchronized void keyTyped(KeyEvent e) {
        dispatchKeyEvent(e, Page.KeyEventType.TYPED);
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        dispatchKeyEvent(e, Page.KeyEventType.PRESSED);
    }

    @Override
    public synchronized void keyReleased(final KeyEvent e) {
        dispatchKeyEvent(e, Page.KeyEventType.RELEASED);
    }

    @Override
    public synchronized void mouseDragged(MouseEvent e) {
        dispatchMouseMoveEvent(e, Page.MouseMoveEventType.DRAGGED);
    }

    @Override
    public synchronized void mouseMoved(MouseEvent e) {
        dispatchMouseMoveEvent(e, Page.MouseMoveEventType.MOVED);
    }

    @Override
    public synchronized void mouseClicked(MouseEvent e) {
        dispatchMouseEvent(e, Page.MouseEventType.CLICKED);
    }

    @Override
    public synchronized void mousePressed(MouseEvent e) {
        dispatchMouseEvent(e, Page.MouseEventType.PRESSED);
    }

    @Override
    public synchronized void mouseReleased(MouseEvent e) {
        dispatchMouseEvent(e, Page.MouseEventType.RELEASED);
    }

    @Override
    public synchronized void mouseEntered(MouseEvent e) {
        dispatchMouseMoveEvent(e, Page.MouseMoveEventType.ENTERED);
    }

    @Override
    public synchronized void mouseExited(MouseEvent e) {
        dispatchMouseMoveEvent(e, Page.MouseMoveEventType.EXITED);
    }

}
