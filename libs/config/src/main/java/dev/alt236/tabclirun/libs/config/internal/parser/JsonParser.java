package dev.alt236.tabclirun.libs.config.internal.parser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dev.alt236.tabclirun.libs.config.Command;
import dev.alt236.tabclirun.libs.config.internal.model.GlobalSettings;
import dev.alt236.tabclirun.libs.config.internal.model.Settings;

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
            throw new IllegalArgumentException(message);
        }
    }

    public List<Command> parse(final File file, final String target) {
        final Settings settings = parseFile(file);
        return map(settings, target);
    }

    private List<Command> map(Settings settings, String target) {
        final GlobalSettings globalSettings = settings.getGlobalSettings();

        if (globalSettings == null) {
            final String errorMessage = "Global settings missing from file";
            System.err.println(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        final List<Command> retVal = new ArrayList<>();
        for (final dev.alt236.tabclirun.libs.config.internal.model.Command inputCommand : settings.getCommands()) {
            checkNullity(inputCommand.getCommand(), "Command cannot be null!");

            final Color textColor = ParseHelper.getFirstNotNullColor(inputCommand.getTextColor(), globalSettings.getTextColor());
            final Color errorColor = ParseHelper.getFirstNotNullColor(inputCommand.getErrorTextColor(), globalSettings.getErrorTextColor());
            final Color bgColor = ParseHelper.getFirstNotNullColor(inputCommand.getConsoleColor(), globalSettings.getConsoleColor());
            final String name = inputCommand.getName() == null ? inputCommand.getCommand() : inputCommand.getName();
            final String commandString = composeCommand(inputCommand.getCommand(), target);
            final boolean disableAutoScroll = ParseHelper.getFirstNotNull(
                    inputCommand.isDisableAutoScroll(),
                    globalSettings.isDisableAutoScroll(),
                    Boolean.FALSE);

            final Command command = new Command.Builder()
                    .withDisableAutoScroll(disableAutoScroll)
                    .withCommand(commandString)
                    .withName(name)
                    .withBackgroundColor(bgColor)
                    .withTextColor(textColor)
                    .withErrorColor(errorColor)
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
