package com.Ex_ce_pt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.function.Consumer;

public class PageBar extends JPanel {

    private final ArrayList<Tab> tabs;

    private final ArrayList<Consumer<Integer>> onTabChange;

    public PageBar() {
        setBackground(Color.lightGray);
        setLayout(null);
        setPreferredSize(new Dimension(1, 30));

        tabs = new ArrayList<>();

        onTabChange = new ArrayList<>();

        addTab(new HomeTab(this));

        Tab tab = new Tab("New tab", this);
        tab.setLocation(100, 0);
        addTab(tab);

    }

    private void fireTabChangeEvent(int index) {
        for (var i : onTabChange)
            if (i != null)
                i.accept(index);
    }

    public void addTab(String title) {
        addTab(new Tab(title, this));
    }

    public void addTab(Tab tab) {
        tabs.add(tab);
        updateTabs();
    }

    public void removeTab(int index) {
        tabs.remove(index);
        updateTabs();
    }

    public void removeTab(String title) {
        tabs.removeIf(t -> t.title.equals(title));
        updateTabs();
    }

    public void removeTab(Tab tab) {
        tabs.remove(tab);
        updateTabs();
    }

    public void updateTabs() {
        removeAll();
        for (int i = 0; i < tabs.size(); i++) {
            final Tab current = tabs.get(i);
            current.setLocation(100 * i, current.getY());
            add(current);
        }
        repaint();
    }

    public void subscribeToTabChangeEvent(Consumer<Integer> func) {
        onTabChange.add(func);
    }

    public void unsubscribeFromTabChangeEvent(Consumer<Integer> func) {
        onTabChange.remove(func);
    }


    public static class Tab extends JPanel implements MouseListener {

        public static final Color TAB_COLOR = new Color(160, 160, 160);

        protected final PageBar parent;
        protected String title;
        protected final JLabel label;
        protected final JButton closeButton;

        public Tab(String title, PageBar parent) {
            this.title = title;
            this.parent = parent;

            setBackground(TAB_COLOR);
            setBorder(BorderFactory.createRaisedBevelBorder());
            setSize(100, 30);
            setLayout(null);

            label = new JLabel(title);
            label.setFont(new Font("Arial", Font.PLAIN, 13));
            label.setForeground(Color.white);
            label.setBounds(30, 2, 50, 28);

            closeButton = new CloseButton(this);

            closeButton.setBounds(10, 5, 20, 20);

            addMouseListener(this);

            add(label);
            add(closeButton);
        }

        public void selected() {
            parent.fireTabChangeEvent(parent.tabs.indexOf(this));
        }

        @Override
        public void mouseClicked(final MouseEvent e) {
            selected();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        }

        public static class CloseButton extends JButton {

            private static final int PADDING = 5;

            private final Tab parent;

            public CloseButton(Tab parent) {
                this.parent = parent;

                addActionListener(e -> parent.parent.removeTab(parent));

                setBackground(TAB_COLOR);
                setBorder(BorderFactory.createEmptyBorder());
            }

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.black);
                final Dimension size = getSize();
                g.drawLine(PADDING, PADDING, size.width - PADDING, size.height - PADDING);
                g.drawLine(size.width - PADDING, PADDING, PADDING, size.height - PADDING);
            }

        }


    }

    public static final class HomeTab extends Tab {

        public HomeTab(PageBar parent) {
            super("Home", parent);
            remove(closeButton);
        }

    }

}
