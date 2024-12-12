package uk.co.alt236.tabclirun.ui;


import dev.alt236.tabclirun.libs.config.Command;
import dev.alt236.tabclirun.libs.exec.result.Result;
import uk.co.alt236.tabclirun.resources.Resources;
import uk.co.alt236.tabclirun.ui.views.TabHost;

import javax.swing.*;
import java.awt.*;

class View {
    private final JFrame frame;
    private final TabHost tabHost;

    View(Resources resources) {
        setupApplicationLookAndFeel();
        JFrame.setDefaultLookAndFeelDecorated(true);

        frame = new JFrame();
        tabHost = new TabHost();
        setupToolbar(resources);
    }

    void display() {
        final ScreenResolution screenResolution = new ScreenResolution();
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setPreferredSize(new Dimension(screenResolution.getScreenResolution().width / 3,
                screenResolution.getScreenResolution().height / 3));
        frame.setContentPane(tabHost);
        frame.pack();

        tabHost.display();
        frame.setVisible(true);
    }


    void addTab(final Command command) {
        tabHost.addTab(command);
    }

    int findTabWithName(String name) {
        return tabHost.findTabWithName(name);
    }

    void setTabData(int tabIndex, Result result) {
        SwingUtilities.invokeLater(() -> tabHost.setTabData(tabIndex, result));
    }

    private void setupApplicationLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private void setupToolbar(Resources resources) {
        frame.setIconImage(resources.getImage("terminal-icon.png"));
        frame.setTitle("TabCliRun");
    }
}
