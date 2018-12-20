package uk.co.alt236.tabclirun;

import javax.annotation.Nonnull;
import java.awt.*;

public class Command {
    private final String name;
    private final String command;

    private final Color backgroundColor;
    private final Color textColor;
    private final Color errorColor;
    private final boolean disableAutoScroll;

    private Command(Builder builder) {
        name = builder.name;
        command = builder.command;
        backgroundColor = builder.backgroundColor;
        textColor = builder.textColor;
        errorColor = builder.errorColor;
        disableAutoScroll = builder.disableAutoScroll;
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getErrorColor() {
        return errorColor;
    }

    public boolean isDisableAutoScroll() {
        return disableAutoScroll;
    }

    public static final class Builder {
        private String name;
        private String command;
        private Color backgroundColor;
        private Color textColor;
        private Color errorColor;
        private boolean disableAutoScroll;

        public Builder() {
        }

        public Builder(@Nonnull Command copy) {
            this.name = copy.getName();
            this.command = copy.getCommand();
            this.backgroundColor = copy.getBackgroundColor();
            this.textColor = copy.getTextColor();
            this.errorColor = copy.getErrorColor();
        }

        @Nonnull
        public Builder withName(@Nonnull String val) {
            name = val;
            return this;
        }

        @Nonnull
        public Builder withCommand(@Nonnull String val) {
            command = val;
            return this;
        }

        @Nonnull
        public Builder withBackgroundColor(Color val) {
            backgroundColor = val;
            return this;
        }

        @Nonnull
        public Builder withTextColor(Color val) {
            textColor = val;
            return this;
        }

        @Nonnull
        public Builder withErrorColor(Color val) {
            errorColor = val;
            return this;
        }

        @Nonnull
        public Builder withDisableAutoScroll(boolean val) {
            disableAutoScroll = val;
            return this;
        }

        @Nonnull
        public Command build() {
            return new Command(this);
        }
    }
}
