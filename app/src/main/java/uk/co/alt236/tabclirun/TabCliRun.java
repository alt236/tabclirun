package uk.co.alt236.tabclirun;

import uk.co.alt236.tabclirun.cli.CommandLineOptions;
import uk.co.alt236.tabclirun.json.JsonParser;
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
        final JsonParser jsonParser = new JsonParser();

        final List<Command> commands = jsonParser.parse(
                new File(cliOptions.getSettingsFile()),
                cliOptions.getTarget());

        tabsPresenter.present(commands);
    }
}
