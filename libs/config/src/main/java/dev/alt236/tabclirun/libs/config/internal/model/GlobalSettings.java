package dev.alt236.tabclirun.libs.config.internal.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

public class GlobalSettings {

    @SerializedName("normalTextColor")
    private String textColor;
    @SerializedName("errorTextColor")
    private String errorTextColor;
    @SerializedName("consoleColor")
    private String consoleColor;
    @SerializedName("disableAutoScroll")
    private Boolean disableAutoScroll;

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
