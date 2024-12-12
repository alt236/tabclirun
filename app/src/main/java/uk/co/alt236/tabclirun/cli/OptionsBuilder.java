package uk.co.alt236.tabclirun.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import uk.co.alt236.tabclirun.resources.Strings;

public class OptionsBuilder {

    /*package*/ static final String ARG_SETTINGS_LONG = "settings";
    /*package*/ static final String ARG_TARGET_LONG = "target";
    /*package*/ private static final String ARG_SETTINGS = "s";
    /*package*/ private static final String ARG_TARGET = "t";

    /*package*/ static final String ARG_VERBOSE = "v";
    /*package*/ static final String ARG_VERBOSE_LONG = "verbose";

    private final Strings strings;

    public OptionsBuilder(Strings strings) {
        this.strings = strings;
    }

    public Options compileOptions() {
        final Options options = new Options();

        options.addOption(createOptionSettings());
        options.addOption(createOptionTarget());
        options.addOption(createOptionVerbose());

        return options;
    }

    private Option createOptionSettings() {
        final String desc = strings.getString("cli_cmd_settings");
        return Option.builder(ARG_SETTINGS)
                .longOpt(ARG_SETTINGS_LONG)
                .hasArg()
                .required(true)
                .desc(desc)
                .build();
    }

    private Option createOptionTarget() {
        final String desc = strings.getString("cli_cmd_target");
        return Option.builder(ARG_TARGET)
                .longOpt(ARG_TARGET_LONG)
                .hasArg()
                .required(true)
                .desc(desc)
                .build();
    }

    private Option createOptionVerbose() {
        final String desc = strings.getString("cli_cmd_input_verbose");
        return Option.builder(ARG_VERBOSE)
                .longOpt(ARG_VERBOSE_LONG)
                .hasArg(false)
                .required(false)
                .desc(desc)
                .build();
    }
}
