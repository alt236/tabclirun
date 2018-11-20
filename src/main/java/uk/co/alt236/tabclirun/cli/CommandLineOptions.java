package uk.co.alt236.tabclirun.cli;

import org.apache.commons.cli.CommandLine;

class CommandLineOptions {

    private final CommandLine commandLine;

    public CommandLineOptions(final CommandLine commandLine) {
        this.commandLine = commandLine;
    }


    public String getInputFile() {
        return commandLine.getOptionValue(OptionsBuilder.ARG_INPUT_LONG);
    }


}
