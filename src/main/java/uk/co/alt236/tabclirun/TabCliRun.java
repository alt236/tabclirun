package uk.co.alt236.tabclirun;

import uk.co.alt236.tabclirun.cli.CommandLineOptions;
import uk.co.alt236.tabclirun.json.JsonParser;
import uk.co.alt236.tabclirun.ui.TabsPresenter;

import java.io.File;
import java.util.List;

class TabCliRun {
    public void execute(CommandLineOptions cliOptions) {
        final TabsPresenter tabsPresenter = new TabsPresenter();
        final JsonParser jsonParser = new JsonParser();

        final List<Command> commands = jsonParser.parse(
                new File(cliOptions.getSettingsFile()),
                cliOptions.getTarget());

        tabsPresenter.present(commands);
    }
}
