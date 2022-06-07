package com.Ex_ce_pt;

import java.util.ArrayList;
import java.awt.BorderLayout;

import com.Ex_ce_pt.gui.*;

public class MainPanel extends javax.swing.JPanel {

    private final PageListener listener;
    private final PageBar pageBar;
    private final ArrayList<Page> pages;
    private Page currentPage;


    public MainPanel() throws java.awt.AWTException {
        new java.awt.Robot();

        setLayout(new BorderLayout());

        pages = new ArrayList<>();
        pages.add(new TitlePage());
        pages.add(new ConnectionPage());

        listener = new PageListener();
        listener.attach(Main.frame);

        pageBar = new PageBar();
        pageBar.subscribeToTabChangeEvent(this::setCurrentPage);

        add(pageBar, BorderLayout.NORTH);

        setCurrentPage(0);
        //setCurrentPage(1);
    }

    public void setCurrentPage(int index) {
        if (index < 0 || index >= pages.size()) return;

        if (currentPage != null) remove(currentPage);
        currentPage = pages.get(index);
        add(currentPage, BorderLayout.CENTER);
        listener.setFocusedPage(currentPage);

        repaint();
    }


}
