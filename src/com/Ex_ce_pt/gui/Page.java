package com.Ex_ce_pt.gui;

import java.awt.event.*;

public abstract class Page extends javax.swing.JPanel {

    public enum KeyEventType { TYPED, PRESSED, RELEASED }
    public enum MouseEventType { CLICKED, PRESSED, RELEASED }
    public enum MouseMoveEventType { MOVED, DRAGGED, ENTERED, EXITED }

    public abstract void onKeyEvent(final KeyEvent e, final KeyEventType type);
    public abstract void onMouseEvent(final MouseEvent e, final MouseEventType type);
    public abstract void onMouseMoveEvent(final MouseEvent e, final MouseMoveEventType type);

}
