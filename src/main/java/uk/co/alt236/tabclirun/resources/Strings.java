package uk.co.alt236.tabclirun.resources;

import javax.annotation.Nonnull;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Strings extends ResourceBundle {

    private final PropertyResourceBundle bundle;

    public Strings() {
        bundle = (PropertyResourceBundle) ResourceBundle.getBundle("strings");
    }

    @Override
    protected Object handleGetObject(@Nonnull String s) {
        return bundle.handleGetObject(s);
    }

    @Override
    @Nonnull
    public Enumeration<String> getKeys() {
        return bundle.getKeys();
    }

    @Nonnull
    public String getString(@Nonnull final String s, @Nonnull final Object... values) {
        final String string = getString(s);

        return String.format(getLocale(), string, values);
    }
}
