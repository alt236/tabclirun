package uk.co.alt236.tabclirun;

import uk.co.alt236.tabclirun.ui.TabsPresenter;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] a) {
        final TabsPresenter tabsPresenter = new TabsPresenter();
        tabsPresenter.present(getCommandList());
    }

    private static List<Command> getCommandList() {
        return Arrays.asList(
                new Command
                        .Builder()
                        .withCommand("ls")
                        .withName("LS TEST")
                        .withBackgroundColor(Color.BLACK)
                        .withTextColor(Color.GREEN)
                        .withErrorColor(Color.RED)
                        .build(),
                new Command
                        .Builder()
                        .withCommand("df")
                        .withName("DF TEST")
                        .withBackgroundColor(Color.BLACK)
                        .withTextColor(Color.GREEN)
                        .withErrorColor(Color.RED)
                        .build(),
                new Command
                        .Builder()
                        .withCommand("asdasd")
                        .withName("asdasd TEST")
                        .withBackgroundColor(Color.BLACK)
                        .withTextColor(Color.GREEN)
                        .withErrorColor(Color.RED)
                        .build());
    }
}
