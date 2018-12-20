package uk.co.alt236.tabclirun.json.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

public class Command {

    @SerializedName("name")
    private String name;
    @SerializedName("command")
    private String command;

    @SerializedName("normalTextColor")
    private String textColor;
    @SerializedName("errorTextColor")
    private String errorTextColor;
    @SerializedName("consoleColor")
    private String consoleColor;
    @SerializedName("disableAutoScroll")
    private Boolean disableAutoScroll;

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getCommand() {
        return command;
    }

    @Nullable
    public String getTextColor() {
        return textColor;
    }

    @Nullable
    public String getErrorTextColor() {
        return errorTextColor;
    }

    @Nullable
    public String getConsoleColor() {
        return consoleColor;
    }

    @Nullable
    public Boolean isDisableAutoScroll() {
        return disableAutoScroll;
    }
}
