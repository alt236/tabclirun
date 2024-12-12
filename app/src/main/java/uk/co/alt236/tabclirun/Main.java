package uk.co.alt236.tabclirun;

import org.apache.commons.cli.*;
import uk.co.alt236.tabclirun.cli.CommandHelpPrinter;
import uk.co.alt236.tabclirun.cli.CommandLineOptions;
import uk.co.alt236.tabclirun.cli.JarDetails;
import uk.co.alt236.tabclirun.cli.OptionsBuilder;
import uk.co.alt236.tabclirun.resources.Resources;
import uk.co.alt236.tabclirun.resources.Strings;

class Main {
    public static void main(String[] args) {
        final Strings strings = new Strings();
        final Resources resources = new Resources();
        final CommandLineOptions cliOptions = parseArgs(strings, resources, args);

        if (cliOptions != null) {
            new TabCliRun(resources).execute(cliOptions);
        }
    }

    private static CommandLineOptions parseArgs(Strings strings, Resources resources, String[] args) {
        final CommandLineParser parser = new DefaultParser();
        final Options options = new OptionsBuilder(strings).compileOptions();
        final CommandLineOptions retVal;

        if (args.length == 0) {
            final JarDetails jarDetails = new JarDetails(Main.class);
            new CommandHelpPrinter(strings, resources, jarDetails, options).printHelp();
            retVal = null;
        } else {
            CommandLine line = null;

            try {
                line = parser.parse(options, args);
            } catch (final ParseException exp) {
                final String message = exp.getMessage();
                System.err.println(message);
                System.exit(1);
            }

            retVal = new CommandLineOptions(line);
        }

        return retVal;
    }

}
