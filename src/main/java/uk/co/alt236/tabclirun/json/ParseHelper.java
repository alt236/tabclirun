package uk.co.alt236.tabclirun.json;

import javax.annotation.Nullable;
import java.awt.*;

final class ParseHelper {

    @SafeVarargs
    @Nullable
    static <T> T getFirstNotNull(T... values) {
        for (final T value : values) {
            if (value != null) {
                return value;
            }
        }

        return null;
    }

    @Nullable
    static Color getFirstNotNullColor(String... colours) {
        final String color = getFirstNotNull(colours);

        return color != null
                ? Color.decode(color)
                : null;
    }
}
