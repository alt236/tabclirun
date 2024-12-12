package uk.co.alt236.tabclirun;

import dev.alt236.tabclirun.libs.config.Command;
import dev.alt236.tabclirun.libs.config.ConfigParser;
import uk.co.alt236.tabclirun.cli.CommandLineOptions;
import uk.co.alt236.tabclirun.resources.Resources;
import uk.co.alt236.tabclirun.ui.TabsPresenter;

import java.io.File;
import java.util.List;

class TabCliRun {
    private final Resources resources;

    TabCliRun(Resources resources) {
        this.resources = resources;
    }

    void execute(CommandLineOptions cliOptions) {
        final TabsPresenter tabsPresenter = new TabsPresenter(resources);
        final ConfigParser jsonParser = new ConfigParser();

        final List<Command> commands = jsonParser.parse(
                new File(cliOptions.getSettingsFile()),
                cliOptions.getTarget());

        tabsPresenter.present(commands);
    }
}
