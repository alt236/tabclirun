package uk.co.alt236.tabclirun.cli;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import uk.co.alt236.tabclirun.resources.Resources;
import uk.co.alt236.tabclirun.resources.Strings;

public class CommandHelpPrinter {
    private static final String BINARY_PLACEHOLDER = "##BINARY##";
    private static final String VERSION_PLACEHOLDER = "##VERSION##";
    private static final String SAMPLE_JSON_PLACEHOLDER = "##SETTINGS_JSON##";

    private final Strings strings;
    private final Resources resources;
    private final Options options;
    private final JarDetails jarDetails;

    public CommandHelpPrinter(final Strings strings,
                              final Resources resources,
                              final JarDetails jarDetails,
                              final Options options) {
        this.resources = resources;
        this.strings = strings;
        this.jarDetails = jarDetails;
        this.options = options;
    }

    public void printHelp() {
        final String header = replacePlaceholders(strings.getString("cli_help_message_header"));
        final String footer = replacePlaceholders(strings.getString("cli_help_message_footer"));

        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(jarDetails.getJarName(), header, options, footer, true);
    }

    private String replacePlaceholders(final String baseString) {
        return baseString
                .replaceAll(BINARY_PLACEHOLDER, jarDetails.getJarName())
                .replaceAll(VERSION_PLACEHOLDER, jarDetails.getJarVersion())
                .replaceAll(SAMPLE_JSON_PLACEHOLDER, resources.readResourceAsString("sample_settings.json"));
    }
}
