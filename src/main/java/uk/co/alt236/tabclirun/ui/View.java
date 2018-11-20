package uk.co.alt236.tabclirun.ui;


import uk.co.alt236.tabclirun.Command;
import uk.co.alt236.tabclirun.exec.Result;
import uk.co.alt236.tabclirun.ui.views.TabHost;

import javax.swing.*;
import java.awt.*;

class View {
    private final JFrame frame;
    private final TabHost tabHost;

    View() {
        setupApplicationLookAndFeel();
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame();
        tabHost = new TabHost();
    }

    void display() {
        final SceenResolution sceenResolution = new SceenResolution();
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setPreferredSize(new Dimension(sceenResolution.getScreenResolution().width / 3,
                sceenResolution.getScreenResolution().height / 3));
        frame.setContentPane(tabHost);
        frame.pack();

        tabHost.display();
        frame.setVisible(true);
    }


    void addTab(final Command command) {
        tabHost.addTab(command);
    }


    private void setupApplicationLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    int findTabWithName(String name) {
        return tabHost.findTabWithName(name);
    }

    void setTabData(int tabIndex, Result result) {
        SwingUtilities.invokeLater(() -> tabHost.setTabData(tabIndex, result));
    }
}
