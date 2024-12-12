package uk.co.alt236.tabclirun.ui.views;

import dev.alt236.tabclirun.libs.config.Command;
import dev.alt236.tabclirun.libs.exec.result.Result;
import uk.co.alt236.tabclirun.ui.views.tabcontent.CommandTab;
import uk.co.alt236.tabclirun.ui.views.tabtitle.TabTitleIcons;

import javax.swing.*;

public class TabHost extends JPanel {
    private final TabTitleIcons tabIcons = new TabTitleIcons();
    private final boolean verbose;

    public TabHost(final boolean verbose) {
        this.verbose = verbose;
    }

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
        final CommandTab commandTab = new CommandTab(verbose);

        commandTab.setCommand(command);
        getTabs().addTab(
                command.getName(),
                tabIcons.getIconInProgress(),
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

        if (result.isSuccess()) {
            tabs.setIconAt(tabIndex, tabIcons.getIconSuccess());
        } else {
            tabs.setIconAt(tabIndex, tabIcons.getIconError());
        }

        tab.setResult(result);
    }

    private JTabbedPane getTabs() {
        return ((JTabbedPane) getComponent(0));
    }
}