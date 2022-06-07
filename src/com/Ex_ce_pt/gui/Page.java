package com.Ex_ce_pt.gui;

import java.awt.event.*;

public abstract class Page extends javax.swing.JPanel {

    public enum KeyEventType { TYPED, PRESSED, RELEASED }
    public enum MouseEventType { CLICKED, PRESSED, RELEASED }
    public enum MouseMoveEventType { MOVED, DRAGGED, ENTERED, EXITED }

    public abstract void onKeyEvent(KeyEvent e, KeyEventType type);
    public abstract void onMouseEvent(MouseEvent e, MouseEventType type);
    public abstract void onMouseMoveEvent(MouseEvent e, MouseMoveEventType type);

}
