package uk.co.alt236.tabclirun.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import uk.co.alt236.tabclirun.resources.Strings;

class OptionsBuilder {

    /*package*/ static final String ARG_INPUT_LONG = "input";
    /*package*/ private static final String ARG_INPUT = "i";
    private final Strings strings;

    public OptionsBuilder(Strings strings) {
        this.strings = strings;
    }

    public Options compileOptions() {
        final Options options = new Options();

        options.addOption(createOptionInput());

        return options;
    }

    private Option createOptionInput() {
        final String desc = strings.getString("cli_cmd_input");
        return Option.builder(ARG_INPUT)
                .longOpt(ARG_INPUT_LONG)
                .hasArg()
                .required(true)
                .desc(desc)
                .build();
    }
}
