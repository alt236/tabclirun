package uk.co.alt236.tabclirun.json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import uk.co.alt236.tabclirun.Command;
import uk.co.alt236.tabclirun.json.model.GlobalSettings;
import uk.co.alt236.tabclirun.json.model.Settings;

import javax.annotation.Nullable;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonParser {
    private static final String PLACE_HOLDER_TARGET = "{param.target}";

    private final Gson gson = new Gson();

    private static void checkNullity(@Nullable final Object object, final String message) {
        if (object == null) {
            System.err.println(message);
            System.exit(1);
        }
    }

    public List<Command> parse(final File file, final String target) {
        final Settings settings = parseFile(file);
        return map(settings, target);
    }

    private List<Command> map(Settings settings, String target) {
        final GlobalSettings globalSettings = settings.getGlobalSettings();

        if (globalSettings == null) {
            System.err.println("Global settings missing from file");
            System.exit(1);
        }

        final List<Command> retVal = new ArrayList<>();
        for (final uk.co.alt236.tabclirun.json.model.Command inputCommand : settings.getCommands()) {
            checkNullity(inputCommand.getCommand(), "Command cannot be null!");

            final Command command = new Command.Builder()
                    .withCommand(composeCommand(inputCommand.getCommand(), target))
                    .withName(inputCommand.getName() == null ? inputCommand.getCommand() : inputCommand.getName())
                    .withBackgroundColor(getFirstNotNullColor(inputCommand.getConsoleColor(), globalSettings.getConsoleColor()))
                    .withTextColor(getFirstNotNullColor(inputCommand.getTextColor(), globalSettings.getTextColor()))
                    .withErrorColor(getFirstNotNullColor(inputCommand.getErrorTextColor(), globalSettings.getErrorTextColor()))
                    .build();

            retVal.add(command);
        }

        validateNameUniqueness(retVal);
        return retVal;
    }

    private String composeCommand(final String command, final String target) {
        return command.replace(PLACE_HOLDER_TARGET, target);
    }

    private void validateNameUniqueness(List<Command> retVal) {
        final Set<String> names = new HashSet<>();

        for (final Command command : retVal) {
            final String name = command.getName();
            if (names.contains(name)) {
                System.err.println("Names have to be unique. '" + name + "' is used twice");
                System.exit(1);
            }
            names.add(name);
        }
    }

    private Color getFirstNotNullColor(String... colours) {
        for (final String color : colours) {
            if (color != null) {
                return Color.decode(color);
            }
        }

        return null;
    }

    private Settings parseFile(final File file) {
        final JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(file));
            return gson.fromJson(reader, Settings.class);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

    }
}
