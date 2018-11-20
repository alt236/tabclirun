package uk.co.alt236.tabclirun.ui.views;

import uk.co.alt236.tabclirun.Command;
import uk.co.alt236.tabclirun.exec.Result;

import javax.swing.*;

public class TabHost extends JPanel {

    public void display() {
        try {
            SwingUtilities.invokeAndWait(this::makeGUI);
        } catch (Exception exc) {
            System.out.println("Can't create because of " + exc);
        }
    }

    private void makeGUI() {
        final BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxlayout);
        add(new JTabbedPane());
    }

    public void addTab(final Command command) {
        final CommandTab commandTab = new CommandTab();

        commandTab.setCommand(command.getCommand());
        getTabs().addTab(
                command.getName(),
                commandTab);
    }

    public int findTabWithName(String name) {
        final JTabbedPane tabs = getTabs();
        int count = tabs.getTabCount();

        for (int i = 0; i < count; i++) {
            if (name.equals(tabs.getTitleAt(i))) {
                return i;
            }
        }

        return -1;
    }

    public void setTabData(int tabIndex, Result result) {
        final JTabbedPane tabs = getTabs();
        final CommandTab tab = (CommandTab) tabs.getComponentAt(tabIndex);
        if (tab == null) {
            throw new IllegalStateException("Did not find a tab at index " + tabIndex);
        }

        tab.setResult(result);
    }

    private JTabbedPane getTabs() {
        return ((JTabbedPane) getComponent(0));
    }
}