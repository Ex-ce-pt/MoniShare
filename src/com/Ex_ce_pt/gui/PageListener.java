package com.Ex_ce_pt.gui;

import java.awt.event.*;

public class PageListener implements KeyListener, MouseListener, MouseMotionListener {

    private Page focusedPage;

    public synchronized void attach(final java.awt.Component comp) {
        comp.addKeyListener(this);
        comp.addMouseListener(this);
        comp.addMouseMotionListener(this);
    }

    public synchronized void detach(final java.awt.Component comp) {
        comp.removeKeyListener(this);
        comp.removeMouseListener(this);
        comp.removeMouseMotionListener(this);
    }

    public synchronized void setFocusedPage(final Page page) {
        focusedPage = page;
    }

    public synchronized Page getFocusedPage() {
        return focusedPage;
    }

    private void dispatchKeyEvent(final KeyEvent e, final Page.KeyEventType type) {
        if (focusedPage != null)
            focusedPage.onKeyEvent(e, type);
    }

    private void dispatchMouseEvent(final MouseEvent e, final Page.MouseEventType type) {
        if (focusedPage != null)
            focusedPage.onMouseEvent(e, type);
    }

    private void dispatchMouseMoveEvent(final MouseEvent e, final Page.MouseMoveEventType type) {
        if (focusedPage != null)
            focusedPage.onMouseMoveEvent(e, type);
    }

    @Override
    public synchronized void keyTyped(final KeyEvent e) {
        dispatchKeyEvent(e, Page.KeyEventType.TYPED);
    }

    @Override
    public synchronized void keyPressed(final KeyEvent e) {
        dispatchKeyEvent(e, Page.KeyEventType.PRESSED);
    }

    @Override
    public synchronized void keyReleased(final KeyEvent e) {
        dispatchKeyEvent(e, Page.KeyEventType.RELEASED);
    }

    @Override
    public synchronized void mouseDragged(final MouseEvent e) {
        dispatchMouseMoveEvent(e, Page.MouseMoveEventType.DRAGGED);
    }

    @Override
    public synchronized void mouseMoved(final MouseEvent e) {
        dispatchMouseMoveEvent(e, Page.MouseMoveEventType.MOVED);
    }

    @Override
    public synchronized void mouseClicked(final MouseEvent e) {
        dispatchMouseEvent(e, Page.MouseEventType.CLICKED);
    }

    @Override
    public synchronized void mousePressed(final MouseEvent e) {
        dispatchMouseEvent(e, Page.MouseEventType.PRESSED);
    }

    @Override
    public synchronized void mouseReleased(final MouseEvent e) {
        dispatchMouseEvent(e, Page.MouseEventType.RELEASED);
    }

    @Override
    public synchronized void mouseEntered(final MouseEvent e) {
        dispatchMouseMoveEvent(e, Page.MouseMoveEventType.ENTERED);
    }

    @Override
    public synchronized void mouseExited(final MouseEvent e) {
        dispatchMouseMoveEvent(e, Page.MouseMoveEventType.EXITED);
    }

}
