package uk.co.alt236.tabclirun.cli;

import org.apache.commons.cli.CommandLine;

public class CommandLineOptions {

    private final CommandLine commandLine;

    public CommandLineOptions(final CommandLine commandLine) {
        this.commandLine = commandLine;
    }


    public String getSettingsFile() {
        return commandLine.getOptionValue(OptionsBuilder.ARG_SETTINGS_LONG);
    }

    public String getTarget() {
        return commandLine.getOptionValue(OptionsBuilder.ARG_TARGET_LONG);
    }

    public boolean isVerbose() {
        return commandLine.hasOption(OptionsBuilder.ARG_VERBOSE_LONG);
    }
}
