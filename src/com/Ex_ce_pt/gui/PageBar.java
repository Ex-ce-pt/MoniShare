package com.Ex_ce_pt.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PageBar extends JPanel {

    private final ArrayList<Tab> tabs;

    public PageBar() {
        setBackground(Color.lightGray);
        setLayout(null);
        setPreferredSize(new Dimension(1, 30));

        tabs = new ArrayList<>();

        addTab(new HomeTab(this));

        Tab tab = new Tab("New tab", this);
        tab.setLocation(100, 0);
        addTab(tab);

    }

    public void addTab(final String title) {
        addTab(new Tab(title, this));
    }

    public void addTab(final Tab tab) {
        tabs.add(tab);
        updateTabs();
    }

    public void removeTab(final int index) {
        tabs.remove(index);
        updateTabs();
    }

    public void removeTab(final String title) {
        tabs.removeIf(t -> t.title.equals(title));
        updateTabs();
    }

    public void removeTab(final Tab tab) {
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


    public static class Tab extends JPanel {

        public static final Color TAB_COLOR = new Color(160, 160, 160);

        protected final PageBar parent;
        protected String title;
        protected final JLabel label;
        protected final JButton closeButton;

        public Tab(final String title, final PageBar parent) {
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

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    selected();
                }
            });

            add(label);
            add(closeButton);
        }

        public void selected() {
            System.out.println(parent.tabs.indexOf(this));
        }

        public static class CloseButton extends JButton {

            private static final int PADDING = 5;

            private final Tab parent;

            public CloseButton(final Tab parent) {
                this.parent = parent;

                addActionListener(e -> parent.parent.removeTab(parent));

                setBackground(TAB_COLOR);
                setBorder(BorderFactory.createEmptyBorder());
            }

            @Override
            public void paintComponent(final Graphics g) {
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
