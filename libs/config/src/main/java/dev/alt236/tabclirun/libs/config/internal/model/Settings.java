package dev.alt236.tabclirun.libs.config.internal.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Settings {

    @SerializedName("globalSettings")
    private GlobalSettings globalSettings;
    @SerializedName("commands")
    private List<Command> commands;

    public GlobalSettings getGlobalSettings() {
        return globalSettings;
    }

    public List<Command> getCommands() {
        return commands;
    }
}
